package com.example.shido.architecturepaging

/**
 * Created by Shido on 12/11/2017.
 */
class KitsuResponse(
        val data: List<KitsuItem>)

data class KitsuItem(
        val id: Int,
        val type: String?,
        val attributes: KitsuItemAttributes?)

data class KitsuItemAttributes(
        val synopsis: String?,
        val subtype: String?,
        val titles: KitsuItemAttributesTitles?,
        val posterImage: KitsuItemAttributesImage?)

data class KitsuItemAttributesTitles(
        val en_jp: String?)

data class KitsuItemAttributesImage(
        val small: String?)