package com.javiermarsicano.memogame.domain

data class Card (
         val id: Int,
         val image: Int
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Card) {
            this.image == other.image
        } else {
            false
        }
    }
}