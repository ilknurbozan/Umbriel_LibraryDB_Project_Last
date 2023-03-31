select * from books where name ='Clean Code JDBS';

select b.name as bookName,b.id,author,isbn, year,b.description ,added_date , bc.name as bookCategoryName from books b inner join
                                                                    book_categories bc on b.book_category_id = bc.id
where b.name = 'Clean Code JDBS';
