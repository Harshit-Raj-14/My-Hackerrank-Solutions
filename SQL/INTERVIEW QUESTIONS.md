**1. Find the second-highest salary in a table without using LIMIT or TOP.**  
**Answer:**  
```sql
SELECT MAX(salary) 
FROM table 
WHERE salary NOT IN (SELECT MAX(salary) FROM table);
```

**2. Write a SQL query to find all employees who earn more than their managers.**  
**Answer:**  
```sql
SELECT e1.* 
FROM employees e1 
JOIN employees e2 
ON e1.manager_id = e2.id 
WHERE e1.salary > e2.salary;
```

---

**3. Find the duplicate rows in a table without using GROUP BY.**  
**Answer:**  
```sql
SELECT * 
FROM table t1 
WHERE EXISTS (
  SELECT 1 
  FROM table t2 
  WHERE t1.column1 = t2.column1 AND t1.column2 = t2.column2 
  AND t1.rowid != t2.rowid
);
```

---

**4. Write a SQL query to find the top 10% of earners in a table.**  
**Answer:**  
```sql
SELECT * 
FROM table 
WHERE salary >= (
  SELECT PERCENTILE_CONT(0.9) WITHIN GROUP (ORDER BY salary) 
  FROM table
);
```

---

**5. Find the cumulative sum of a column in a table.**  
**Answer:**  
```sql
SELECT column, SUM(column) OVER (ORDER BY rowid) AS cumulative_sum 
FROM table;
```

---

**6. Write a SQL query to find all employees who have never taken a leave.**  
**Answer:**  
```sql
SELECT * 
FROM employees 
WHERE id NOT IN (SELECT employee_id FROM leaves);
```

---

**7. Find the difference between the current row and the next row in a table.**  
**Answer:**  
```sql
SELECT column, 
       column - LEAD(column) OVER (ORDER BY rowid) AS difference 
FROM table;
```

---

**8. Write a SQL query to find all departments with more than one employee.**  
**Answer:**  
```sql
SELECT department 
FROM employees 
GROUP BY department 
HAVING COUNT(*) > 1;
```

---

**9. Find the maximum value of a column for each group without using GROUP BY.**  
**Answer:**  
```sql
SELECT DISTINCT group_column, 
       FIRST_VALUE(column) OVER (PARTITION BY group_column ORDER BY column DESC) AS max_value 
FROM table;
```

---

**10. Write a SQL query to find all employees who have taken more than 3 leaves in a month.**  
**Answer:**  
```sql
SELECT employee_id 
FROM leaves 
GROUP BY employee_id, YEAR(leave_date), MONTH(leave_date) 
HAVING COUNT(*) > 3;
```
