package com.chirag047.rapidservice.Repository

import android.util.Log
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.CenterModel
import com.chirag047.rapidservice.Model.Coordinates
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DataRepository @Inject constructor(val firestore: FirebaseFirestore, val auth: FirebaseAuth) {

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
        mechanicId: String
    ): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("orders")
                .document(orderId)
                .update("mechanicId", mechanicId, "orderStatus", "Mechanic Pending")
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Submitted successfully"))
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }

            awaitClose {
                close()
            }
        }

    suspend fun getMyOrdersRequest(
        corporateId: String,
        requestType: String
    ): Flow<ResponseType<List<OrderModel>>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("orders")
                .whereEqualTo("corporateId", corporateId)
                .whereEqualTo("orderStatus", requestType)
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
}