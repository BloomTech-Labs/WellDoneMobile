package com.versilistyson.welldone.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.versilistyson.welldone.data.db.user.UserDetailsData
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsLocalDataSource
import com.versilistyson.welldone.domain.framework.datasource.user.UserDetailsRemoteDataSource
import com.versilistyson.welldone.domain.framework.repository.UserDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

@FlowPreview
@ExperimentalCoroutinesApi
class UserDetailRepositoryImpl (
    private val localDataSource: UserDetailsLocalDataSource,
    private val remoteDataSource: UserDetailsRemoteDataSource
) : UserDetailRepository {

//    private val store =
//        StoreBuilder
//            .fromNonFlow<String, UserDetailsData>{
//                remoteDataSource.getUserDetails().body()?.map()!!
//            }
//            .persister(
//                reader = localDataSource::getUserDetails,
//                writer = localDataSource::saveUserDetails
//            ).cachePolicy(
//                MemoryPolicy.builder()
//                    .setMemorySize(1)
//                    .setExpireAfterAccess(8)
//                    .setExpireAfterTimeUnit(TimeUnit.DAYS)
//                    .build()
//            )
//            .build()

    override fun fetchFreshUserDetails(): Flow<StoreResponse<UserDetailsData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchUserDetails(): Flow<StoreResponse<UserDetailsData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


//    override fun fetchUserDetails(): Flow<StoreResponse<UserDetailsData>> =
//        store.stream(StoreRequest.cached(USER_DETAILS_KEY, false))
//
//    override fun fetchFreshUserDetails(): Flow<StoreResponse<UserDetailsData>> =
//        store.stream(StoreRequest.fresh(USER_DETAILS_KEY))
