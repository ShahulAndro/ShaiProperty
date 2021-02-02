package com.sha.iproperty.data.model

data class PropertyItem(
    var channels: List<String>?,
    var kind: String?,
    var id: String?,
    var shareLink: String?,
    var title: String?,
    var active: Boolean?,
    var tier: Int?,
    var propertyType: String?,
    var prices: List<Price>?,
    var cover: Cover?,
    var medias: List<Media>?,
    var updatedAt: String,
    var publishedAt: String,
    var address: Address?,
    var referenceCode: String?,
    var attributes: Attribute?,
    var listers: List<Lister>?,
    var organisations: List<Organisation>?
)

data class Price (
    var type: String?,
    var currency: String?,
    var max: Long?,
    var min: Long?
)

data class Cover (
    var type: String?,
    var url: String?,
    var thumbnailUrl: String?,
    var urlTemplate: String?
)

data class Media (
    var type: String?,
    var url: String?,
    var thumbnailUrl: String?,
    var urlTemplate: String?
)

data class Address (
    var formattedAddress: String?,
    var lat: Double?,
    var lng: Double?
)

data class Attribute (
    var bathroom: String?,
    var bedroom: String?,
    var carPark: String?,
    var builtUp: String?,
    var landTitleType: String?,
    var tenure: String?,
    var unitType: String?,
    var furnishing: String?,
    var sizeUnit: String?
)

data class Lister (
    var id: String?,
    var type: String?,
    var name: String?,
    var contact: Contact?
)

data class Contact(
    var phones: List<Phone>?
)

data class Phone(
    var label: String?,
    var number: String?
)

data class Organisation(
    var id: String?,
    var type: String?,
    var name: String?
)