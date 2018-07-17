package com.rajsuvariya.bakingapp.data.remote;

import javax.annotation.Generated;
import javax.inject.Provider;

import dagger.internal.Factory;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiHeader_PublicApiHeader_Factory implements Factory<ApiHeader.PublicApiHeader> {
  private final Provider<String> apiKeyProvider;

  public ApiHeader_PublicApiHeader_Factory(Provider<String> apiKeyProvider) {
    assert apiKeyProvider != null;
    this.apiKeyProvider = apiKeyProvider;
  }

  @Override
  public ApiHeader.PublicApiHeader get() {
    return new ApiHeader.PublicApiHeader(apiKeyProvider.get());
  }

  public static Factory<ApiHeader.PublicApiHeader> create(Provider<String> apiKeyProvider) {
    return new ApiHeader_PublicApiHeader_Factory(apiKeyProvider);
  }
}
