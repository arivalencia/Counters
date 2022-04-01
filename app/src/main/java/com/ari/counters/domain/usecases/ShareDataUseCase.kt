package com.ari.counters.domain.usecases

import com.ari.counters.domain.contracts.ShareData
import javax.inject.Inject


class ShareDataUseCase @Inject constructor() {

    suspend operator fun invoke(shareData: ShareData) = shareData.share()

}