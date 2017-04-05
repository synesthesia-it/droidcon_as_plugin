<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ffffff"
                android:orientation="vertical">

    <TextView
            android:id="@+id/text_tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:layout_centerInParent="true"
            <#if setExtraText>
            android:text="@string/lbl_extra_text_${activityName?lower_case}"
            <#else>
            android:text="example text"
            </#if>
            android:textColor="#008800"
            android:textSize="32sp"
            android:rotation="45"
            android:textStyle="italic"
    />

</RelativeLayout>