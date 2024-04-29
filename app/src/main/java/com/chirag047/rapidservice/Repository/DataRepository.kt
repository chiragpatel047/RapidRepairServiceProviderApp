package com.chirag047.rapidservice.Repository

import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.chirag047.rapidservice.Api.NotificationApi
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.CenterModel
import com.chirag047.rapidservice.Model.Coordinates
import com.chirag047.rapidservice.Model.FirebaseNotificationModel
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.Model.PushNotification
import com.chirag047.rapidservice.Model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DataRepository @Inject constructor(
    val firestore: FirebaseFirestore,
    val auth: FirebaseAuth,
    val storage: FirebaseStorage,
    val notificationApi: NotificationApi
) {

    suspend fun createNewCenter(centerModel: CenterModel): Flow<ResponseType<String>> =
        callbackFlow {
            trySend(ResponseType.Loading())

            firestore.collection("centers").document(centerModel.centerId!!).set(centerModel)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        firestore.collection("serviceUsers").document(auth.currentUser!!.uid)
                            .update("userCenterId", centerModel.centerId).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    trySend(ResponseType.Success("Your corporate registered successfully"))
                                }
                            }
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }
            awaitClose {
                close()
            }
        }


    suspend fun getSingleCenterDetails(centerId: String): Flow<ResponseType<CenterModel?>> =
        callbackFlow {
            trySend(ResponseType.Loading())

            firestore.collection("centers").document(centerId).addSnapshotListener { value, error ->
                trySend(ResponseType.Success(value!!.toObject(CenterModel::class.java)))
            }

            awaitClose {
                close()
            }
        }

    suspend fun updateCenterStatus(
        centerId: String,
        centerStatus: String
    ): Flow<ResponseType<String>> =
        callbackFlow {
            trySend(ResponseType.Loading())

            firestore.collection("centers")
                .document(centerId)
                .update("centerStatus", centerStatus).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Status updated"))
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }

            awaitClose {
                close()
            }
        }


    suspend fun updateCenterDetails(
        centerId: String,
        centerName: String,
        centerAddress: String,
        centerPhoneNo: String,
        centerTime: String
    ): Flow<ResponseType<String>> =
        callbackFlow {
            trySend(ResponseType.Loading())

            firestore.collection("centers")
                .document(centerId)
                .update(
                    "centerName",
                    centerName,
                    "centerAddress",
                    centerAddress,
                    "centerPhoneNo",
                    centerPhoneNo,
                    "centerTime",
                    centerTime
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Updated successfully"))
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }

            awaitClose {
                close()
            }
        }

    suspend fun getPendingOrderRequests(centerId: String): Flow<ResponseType<List<OrderModel>>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("orders")
                .whereEqualTo("corporateId", centerId)
                .whereEqualTo("orderStatus", "Pending")
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObjects(OrderModel::class.java)))
                }

            awaitClose {
                close()
            }
        }

    suspend fun searchMechanic(mechanicId: String): Flow<ResponseType<List<MechanicModel>>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("mechanicUsers")
                .whereEqualTo("mechanicId", mechanicId)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObjects(MechanicModel::class.java)))
                }

            awaitClose {
                close()
            }
        }

    suspend fun addNewMechanic(
        mechanicUid: String,
        centerId: String,
        centerName: String
    ): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("mechanicUsers")
                .document(mechanicUid)
                .update("centerId", centerId, "centerName", centerName).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Added successfully"))
                    } else {
                        trySend(ResponseType.Error(it.exception!!.message.toString()))
                    }
                }

            awaitClose {
                close()
            }
        }

    suspend fun deleteMechanic(
        mechanicUid: String
    ): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("mechanicUsers")
                .document(mechanicUid)
                .update("centerId", "", "centerName", "Not joined yet")
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Deleted successfully"))
                    } else {
                        trySend(ResponseType.Error(it.exception!!.message.toString()))
                    }
                }

            awaitClose {
                close()
            }
        }

    suspend fun getMyAllMechanics(
        centerId: String
    ): Flow<ResponseType<List<MechanicModel>>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("mechanicUsers")
                .whereEqualTo("centerId", centerId)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObjects(MechanicModel::class.java)))
                }

            awaitClose {
                close()
            }
        }

    suspend fun submitOrderToMechanic(
        orderId: String,
        mechanicId: String,
        userId: String
    ): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("orders")
                .document(orderId)
                .update(
                    "mechanicId",
                    mechanicId,
                    "orderStatus",
                    "Mechanic Pending",
                    "orderInfo",
                    "Status : Your request is accepted, waiting for mechanic to start service"
                )
                .await()

            val notify = withContext(Dispatchers.IO) {

                val notification = PushNotification(
                    FirebaseNotificationModel(
                        "Your Request is Accepted",
                        "You can live track once it is start by our mechanic "
                    ), "/topics/" + userId
                )

                try {
                    val respose = notificationApi.postNotification(notification)
                    trySend(ResponseType.Success("Submitted successfully"))

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            awaitClose {
                close()
            }
        }


    suspend fun declineOrder(
        orderId: String,
    ): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            val sdf = SimpleDateFormat("hh:mm a | dd MMMM yyyy")
            val currentDate = sdf.format(Date())

            firestore.collection("orders")
                .document(orderId)
                .update(
                    "orderStatus",
                    "Done",
                    "orderInfo",
                    "Declined at : " + currentDate
                )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Order rejected"))
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }

            awaitClose {
                close()
            }
        }


    suspend fun getMyOrdersRequest(
        corporateId: String
    ): Flow<ResponseType<List<OrderModel>>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("orders")
                .whereEqualTo("corporateId", corporateId)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObjects(OrderModel::class.java)))
                }

            awaitClose {
                close()
            }
        }


    suspend fun trackLiveLocation(orderId: String): Flow<ResponseType<Coordinates?>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("liveTrack")
                .document(orderId)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObject(Coordinates::class.java))!!)
                }

            awaitClose {
                close()
            }
        }

    suspend fun getUserDetails(): Flow<ResponseType<UserModel?>> = callbackFlow {
        trySend(ResponseType.Loading())

        firestore.collection("serviceUsers").document(auth.currentUser!!.uid)
            .addSnapshotListener { value, error ->
                trySend(ResponseType.Success(value!!.toObject(UserModel::class.java)))
            }
        awaitClose {
            close()
        }
    }

    suspend fun updateUserProfilePictureAndPhone(
        userImage: String, userName: String, phoneNo: String, sharedPreferences: SharedPreferences
    ): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())

        if (!userImage.equals("")) {

            val ref = storage.reference.child("serviceUsers").child("userProfilePhotos")
                .child(System.currentTimeMillis().toString())

            ref.putFile(Uri.parse(userImage)).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    sharedPreferences.edit().putString("profileImage", it.toString()).apply()

                    firestore.collection("serviceUsers").document(auth.currentUser!!.uid)
                        .update("userImage", it, "userName", userName, "phoneNo", phoneNo)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                trySend(ResponseType.Success("Updated successfully"))
                                Log.d("profileUpdatedLogSuccess", "updated")
                            }
                        }
                }
            }
        } else {

            firestore.collection("serviceUsers").document(auth.currentUser!!.uid)
                .update("userName", userName, "phoneNo", phoneNo).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Updated successfully"))
                        Log.d("profileUpdatedLogSuccess", "updated")
                    }
                }
        }
        awaitClose {
            close()
        }
    }

}