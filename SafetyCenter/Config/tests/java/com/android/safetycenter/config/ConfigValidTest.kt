/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.safetycenter.config

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.safetycenter.tests.config.safetycenterconfig.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfigValidTest {
    private val context: Context = getApplicationContext()

    @Test
    fun validConfig_matchesExpected() {
        val parser = context.resources.getXml(R.xml.config_valid)
        val expected = SafetyCenterConfig.Builder()
            .addSafetySourcesGroup(SafetySourcesGroup.Builder()
                .setId("dynamic")
                .setTitleResId(R.string.reference)
                .setSummaryResId(R.string.reference)
                .setStatelessIconType(SafetySourcesGroup.STATELESS_ICON_TYPE_PRIVACY)
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_DYNAMIC)
                    .setId("dynamic_barebone")
                    .setPackageName("package")
                    .setTitleResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_DYNAMIC)
                    .setId("dynamic_all_optional")
                    .setPackageName("package")
                    .setTitleResId(R.string.reference)
                    .setTitleForWorkResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_ALL)
                    .setInitialDisplayState(SafetySource.INITIAL_DISPLAY_STATE_DISABLED)
                    .setMaxSeverityLevel(300)
                    .setSearchTermsResId(R.string.reference)
                    .setBroadcastReceiverClassName("broadcast")
                    .setAllowLogging(false)
                    .setAllowRefreshOnPageOpen(true)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_DYNAMIC)
                    .setId("dynamic_hidden")
                    .setPackageName("package")
                    .setProfile(SafetySource.PROFILE_ALL)
                    .setInitialDisplayState(SafetySource.INITIAL_DISPLAY_STATE_HIDDEN)
                    .build())
                .build())
            .addSafetySourcesGroup(SafetySourcesGroup.Builder()
                .setId("static")
                .setTitleResId(R.string.reference)
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_STATIC)
                    .setId("static_barebone")
                    .setTitleResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_STATIC)
                    .setId("static_all_optional")
                    .setTitleResId(R.string.reference)
                    .setTitleForWorkResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_ALL)
                    .setSearchTermsResId(R.string.reference)
                    .build())
                .build())
            .addSafetySourcesGroup(SafetySourcesGroup.Builder()
                .setId("issue_only")
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_ISSUE_ONLY)
                    .setId("issue_only_barebone")
                    .setPackageName("package")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_ISSUE_ONLY)
                    .setId("issue_only_all_optional")
                    .setPackageName("package")
                    .setProfile(SafetySource.PROFILE_ALL)
                    .setBroadcastReceiverClassName("broadcast")
                    .setAllowLogging(false)
                    .setAllowRefreshOnPageOpen(true)
                    .build())
                .build())
            .addSafetySourcesGroup(SafetySourcesGroup.Builder()
                .setId("mixed")
                .setTitleResId(R.string.reference)
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_DYNAMIC)
                    .setId("mixed_dynamic_barebone")
                    .setPackageName("package")
                    .setTitleResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_ISSUE_ONLY)
                    .setId("mixed_issue_only_barebone")
                    .setPackageName("package")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .addSafetySource(SafetySource.Builder(SafetySource.SAFETY_SOURCE_TYPE_STATIC)
                    .setId("mixed_static_barebone")
                    .setTitleResId(R.string.reference)
                    .setSummaryResId(R.string.reference)
                    .setIntentAction("intent")
                    .setProfile(SafetySource.PROFILE_PRIMARY)
                    .build())
                .build())
            .build()
        assertEquals(expected, Parser.parseXmlResource(parser))
    }
}
