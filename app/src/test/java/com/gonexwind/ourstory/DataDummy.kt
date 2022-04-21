package com.gonexwind.ourstory

import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse

object DataDummy {
    const val token =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLWZrTkJNYWxtUjBJaVhJSm8iLCJpYXQiOjE2NTAyNjAwOTZ9.kHsb2s6z1xdgEUCUK69Kvfwg-YpRpofOYIxliXL3x8Q"

    fun generateDummyStory(): List<Story> {
        val stories = ArrayList<Story>()
        for (i in 0..10) {
            val story = Story(
                "story-9l9i9nNPTK953Dq4",
                "panjul",
                "..",
                "https://story-api.dicoding.dev/images/stories/photos-1650507630040_SsbvnueC.jpg",
                "2022-04-21T02:20:30.042Z",
                -8.513706,
                115.13228,
            )
            stories.add(story)
        }
        return stories
    }

    fun generateDummyStoryResponse() =
        StoriesResponse(false, "Stories fetched successfully", generateDummyStory())
}