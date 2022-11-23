package com.eea_tech_interview.service.model.discoverymodel

data class DiscoveryModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)