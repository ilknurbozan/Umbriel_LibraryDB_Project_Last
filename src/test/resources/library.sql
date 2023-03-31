
-- US 01
select count(id) from users; --
-- 1855
-- US 01-1
select count(distinct id) from users;
-- 1855


-- RESULT --> MANUALLY IT IS PASSED


-- US 01-2
select * from users;


-- US 02
select count(*) from book_borrow
where is_returned=0;

-- US 03
select name from book_categories;

select b.name as bookName, author, bc.name as bookCategoryName from books b inner join
    book_categories bc on b.book_category_id = bc.id
where b.name = 'Lord of the Files';

select bc.name, count(*) from book_borrow bb inner join books b on b.id = bb.book_id
inner join book_categories bc on b.book_category_id = bc.id
group by bc.name
order by count(*) desc ;
