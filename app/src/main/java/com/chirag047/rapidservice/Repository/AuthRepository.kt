package com.chirag047.rapidservice.Repository

import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(val firebaseAuth : FirebaseAuth, val firebaseFirestore: FirebaseFirestore){
    fun createUser(
        userName: String,
        email: String,
        password: String
    ): Flow<ResponseType<FirebaseUser>> = callbackFlow {

        trySend(ResponseType.Loading())

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userModel =
                    UserModel(firebaseAuth.currentUser!!.uid, userName, email, password, "", "")
                firebaseFirestore.collection("serviceUsers").document(firebaseAuth.currentUser!!.uid)
                    .set(userModel)
                trySend(ResponseType.Success(firebaseAuth.currentUser!!))
            } else {
                trySend(ResponseType.Error(it.exception!!.message.toString()))
            }
        }

        awaitClose {
            close()
        }

    }

    fun loginUser(email: String, password: String): Flow<ResponseType<FirebaseUser>> =
        callbackFlow {
            trySend(ResponseType.Loading())
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResponseType.Success(it.result.user!!))
                } else {
                    trySend(ResponseType.Error(it.exception!!.message.toString()))
                }
            }
            awaitClose {
                close()
            }

        }

    fun sendEmailPasswordResetLink(email: String): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                trySend(ResponseType.Success("Password reset link is sent to your email."))
            } else {
                trySend(ResponseType.Success(it.exception!!.message.toString()))
            }
        }
        awaitClose {
            close()
        }
    }
}