package com.gonexwind.ourstory.core.data.repository

import com.gonexwind.ourstory.core.data.source.local.LocalDataSource
import com.gonexwind.ourstory.core.data.source.remote.RemoteDataSource

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
}