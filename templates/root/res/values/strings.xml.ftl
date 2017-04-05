<resources>
<#if superClass == "BaseActivity">
<#else>


    <string name="lbl_title_${activityName?lower_case}">${activityName}</string>


<#if setExtraText>
    <string name="lbl_extra_text_${activityName?lower_case}">${extraText}</string>
</#if>

</#if>
</resources>