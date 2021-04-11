package com.indrayana.basicintent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Saturday, 10/04/2021 12.21
 * https://gitlab.com/indra-yana
 ****************************************************/

@Parcelize
data class Person(
        val name: String?,
        val email: String?,
        val city: String?
) : Parcelable