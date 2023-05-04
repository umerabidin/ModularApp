/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.merit.hassadmallsdk.core.network.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.merit.hassadmallsdk.core.model.PokemonInfo
import com.merit.hassadmallsdk.core.network.Dispatcher
import com.merit.hassadmallsdk.core.network.PokedexAppDispatchers
import com.merit.hassadmallsdk.core.network.model.PokemonErrorResponse
import com.merit.hassadmallsdk.core.network.model.mapper.ErrorResponseMapper
import com.merit.hassadmallsdk.core.network.service.PokedexClient
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class DetailRepositoryImpl @Inject constructor(
  private val pokedexClient: PokedexClient,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {

  @WorkerThread
  override fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
      /**
       * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
       */
      val response = pokedexClient.fetchPokemonInfo(name = name)
      response.suspendOnSuccess {
        emit(data)
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    }
  .onCompletion { onComplete() }.flowOn(ioDispatcher)
}
