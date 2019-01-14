${test}

<#if test == "测试">
	true
<#else>
	false
</#if>

<#list list as name>
	${name}
</#list>


<#include "include.ftl">

