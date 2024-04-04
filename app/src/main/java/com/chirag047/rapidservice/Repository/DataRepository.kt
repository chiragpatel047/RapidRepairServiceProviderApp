package com.chirag047.rapidservice.Repository

import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.CenterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DataRepository @Inject constructor(val firestore: FirebaseFirestore,val auth : FirebaseAuth) {

    suspend fun createNewCenter(centerModel: CenterModel): Flow<ResponseType<String>> =
        callbackFlow {
            trySend(ResponseType.Loading())

            firestore.collection("centers").document(centerModel.centerId!!).set(centerModel)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        firestore.collection("serviceUsers")
                            .document(auth.currentUser!!.uid)
                            .update("userCenterId",centerModel.centerId).addOnCompleteListener {
                                if(it.isSuccessful){
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
}