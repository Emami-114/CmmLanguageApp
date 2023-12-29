package org.emami.CmmLanguageApp

import org.emami.CmmLanguageApp.di.getSharedModules
import org.koin.core.context.startKoin

fun doInitKoin() {
    startKoin { modules(getSharedModules()) }
}