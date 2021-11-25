package org.gsm.software.barang.model

data class Post(
    var comment_count: Int,
    var created_at: String,
    var dislike_count: Int,
    var like_count: Int,
    var nickname: String,
    var title: String
)