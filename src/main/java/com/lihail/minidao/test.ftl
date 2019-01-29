<#if (a.c)??>
	and empno = :employee.empno
</#if>
<#if (b.d)??>
	and name = :employee.name
</#if>
<#if (employee.age)??>
	and age = :employee.age
</#if>
<#if (employee.salary)??>
	and salary = :employee.salary
</#if>
