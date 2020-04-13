package com.boros.android.starter.features.main.repoDetail

data class RepoDetailScreenModel(
        val avatarUrl: String,
        val repoName: String,
        val ownerName: String?,
        val description: String?
)