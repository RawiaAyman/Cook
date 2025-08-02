package com.example.myapplication.Data.model

import com.squareup.moshi.Json
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "recipes")
data class Meal(
    @PrimaryKey
    @Json(name = "idMeal") val idMeal: String,
    @Json(name = "strMeal") val strMeal: String?,
    @Json(name = "strCategory") val strCategory: String?,
    @Json(name = "strArea") val strArea: String?,
    @Json(name = "strInstructions") val strInstructions: String?,
    @Json(name = "strMealThumb") val strMealThumb: String?,
    @Json(name = "strTags") val strTags: String?,
    @Json(name = "strYoutube") val strYoutube: String?,

    @Json(name = "strIngredient1")  val i1: String?, @Json(name = "strMeasure1")  val m1: String?,
    @Json(name = "strIngredient2")  val i2: String?, @Json(name = "strMeasure2")  val m2: String?,
    @Json(name = "strIngredient3")  val i3: String?, @Json(name = "strMeasure3")  val m3: String?,
    @Json(name = "strIngredient4")  val i4: String?, @Json(name = "strMeasure4")  val m4: String?,
    @Json(name = "strIngredient5")  val i5: String?, @Json(name = "strMeasure5")  val m5: String?,
    @Json(name = "strIngredient6")  val i6: String?, @Json(name = "strMeasure6")  val m6: String?,
    @Json(name = "strIngredient7")  val i7: String?, @Json(name = "strMeasure7")  val m7: String?,
    @Json(name = "strIngredient8")  val i8: String?, @Json(name = "strMeasure8")  val m8: String?,
    @Json(name = "strIngredient9")  val i9: String?, @Json(name = "strMeasure9")  val m9: String?,
    @Json(name = "strIngredient10") val i10: String?, @Json(name = "strMeasure10") val m10: String?,
    @Json(name = "strIngredient11") val i11: String?, @Json(name = "strMeasure11") val m11: String?,
    @Json(name = "strIngredient12") val i12: String?, @Json(name = "strMeasure12") val m12: String?,
    @Json(name = "strIngredient13") val i13: String?, @Json(name = "strMeasure13") val m13: String?,
    @Json(name = "strIngredient14") val i14: String?, @Json(name = "strMeasure14") val m14: String?,
    @Json(name = "strIngredient15") val i15: String?, @Json(name = "strMeasure15") val m15: String?,
    @Json(name = "strIngredient16") val i16: String?, @Json(name = "strMeasure16") val m16: String?,
    @Json(name = "strIngredient17") val i17: String?, @Json(name = "strMeasure17") val m17: String?,
    @Json(name = "strIngredient18") val i18: String?, @Json(name = "strMeasure18") val m18: String?,
    @Json(name = "strIngredient19") val i19: String?, @Json(name = "strMeasure19") val m19: String?,
    @Json(name = "strIngredient20") val i20: String?, @Json(name = "strMeasure20") val m20: String?
) {
    fun ingredientsList(): List<Pair<String, String>> {
        val pairs = listOf(
            i1 to m1, i2 to m2, i3 to m3, i4 to m4, i5 to m5,
            i6 to m6, i7 to m7, i8 to m8, i9 to m9, i10 to m10,
            i11 to m11, i12 to m12, i13 to m13, i14 to m14, i15 to m15,
            i16 to m16, i17 to m17, i18 to m18, i19 to m19, i20 to m20
        )
        return buildList {
            pairs.forEach { (n, m) ->
                val name = n?.trim().orEmpty()
                val measure = m?.trim().orEmpty()
                if (name.isNotEmpty()) add(name to measure)
            }
        }
    }


    fun tagsList(): List<String> =
        strTags?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() } ?: emptyList()
}