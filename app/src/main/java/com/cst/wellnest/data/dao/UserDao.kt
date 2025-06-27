package com.cst.wellnest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cst.wellnest.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC LIMIT 1")
    suspend fun getUser(): User?

    @Query("""
      SELECT * FROM user
      WHERE email = :email AND password = :password 
      LIMIT 1
    """)
    suspend fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): User?

    @Query("""
      SELECT * FROM user
      WHERE email = :email
      LIMIT 1
    """)
    suspend fun getUserByEmail(
        email: String
    ): User?

    /** Update first/last name by email */
    @Query("""
    UPDATE user
      SET first_Name = :firstName,
          last_Name  = :lastName
    WHERE email = :email
  """)
    suspend fun updateNameByEmail(
        email: String,
        firstName: String,
        lastName: String
    ): Int
}