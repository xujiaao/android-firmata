package com.xujiaao.android.firmata.transport

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri

interface TransportProvider {

    @Throws(IllegalArgumentException::class)
    fun getTransport(context: Context, uri: String): Transport =
        getTransport(context, Uri.parse(uri))

    @Throws(IllegalArgumentException::class)
    fun getTransport(context: Context, uri: Uri): Transport
}

object DefaultTransportProvider : TransportProvider {

    private const val PROVIDER_PREFIX = "com.xujiaao.android.firmata.transport.provider"

    private var mProviderClassesInitialized = false
    private val mProviderClasses = HashMap<String, String>()

    private val mProviderCache = HashMap<String, TransportProvider>()

    @Throws(IllegalArgumentException::class)
    override fun getTransport(context: Context, uri: Uri): Transport {
        val type = uri.scheme ?: uri.toString()
        val provider = getProvider(context, "$PROVIDER_PREFIX.${type.toUpperCase()}")

        return provider.getTransport(context, uri)
    }

    @Throws(IllegalArgumentException::class)
    private fun getProvider(context: Context, name: String): TransportProvider {
        synchronized(mProviderCache) {
            val cachedProvider = mProviderCache[name]
            if (cachedProvider != null) {
                return cachedProvider
            }
        }

        val className = getProviderClasses(context)[name]
                ?: throw IllegalArgumentException("No provider with name '$name' found")

        return try {
            val clazz = context.classLoader
                .loadClass(className)
                .asSubclass(TransportProvider::class.java)

            val constructor = clazz.getConstructor()
            constructor.isAccessible = true
            constructor.newInstance()
        } catch (e: NoSuchMethodException) {
            throw IllegalArgumentException("Provider $className must have an empty constructor", e)
        } catch (e: ClassCastException) {
            throw IllegalArgumentException("Class $className is not a transport provider", e)
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("No class with name $className found", e)
        } catch (e: Exception) {
            throw IllegalArgumentException("Failed to load provider $className", e)
        }
    }

    private fun getProviderClasses(context: Context): HashMap<String, String> {
        val providerClasses = mProviderClasses
        synchronized(providerClasses) {
            if (!mProviderClassesInitialized) {
                val applicationInfo = context.packageManager?.getApplicationInfo(
                    context.packageName, PackageManager.GET_META_DATA
                )

                applicationInfo?.metaData?.let {
                    for (key in it.keySet()) {
                        if (key != null && key.startsWith(PROVIDER_PREFIX)) {
                            providerClasses[key] = it.getString(key)
                        }
                    }
                }

                mProviderClassesInitialized = true
            }
        }

        return providerClasses
    }
}