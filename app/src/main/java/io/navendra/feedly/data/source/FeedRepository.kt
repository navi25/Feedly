package io.navendra.feedly.data.source

import io.navendra.feedly.data.source.local.FeedDAO
import io.navendra.feedly.data.source.remote.FeedlyApiInterface
import javax.inject.Inject

class FeedRepository @Inject constructor(val feedlyApi : FeedlyApiInterface,
                                         val feedDAO: FeedDAO){



}