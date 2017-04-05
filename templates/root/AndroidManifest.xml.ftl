<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

    <application>

        <activity android:name="${relativePackage}.${activityName}Activity"
            <#if isOrientationPortrait>
                android:screenOrientation="portrait"
            </#if>

            <#if isLauncher>
            >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            </activity>
            <#else>
                />
            </#if>

    </application>
</manifest>
