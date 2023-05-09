create trigger deleteAccountSetEmployeeAccountNull
on Account
after update
as
begin
	if UPDATE(deletedAt)
	begin
		declare @isDeleted datetime2(7)
		declare @accountId int
		select @isDeleted = deletedAt, @accountId = id  from inserted
		if @isDeleted is not null
		begin
			update Employee set accountID = null where accountID = @accountId
		end
	end
end
go


CREATE TRIGGER [dbo].[trg_INVOICEDETAIL_INSERT] ON [dbo].[InvoiceDetail]
AFTER INSERT
AS
BEGIN
	DEClare @total FLOAT
	DEClare @invoiceId INT
	SET @invoiceId = (SELECT invoiceId FROM inserted)
	SET @total = (SELECT SUM(price*quantity) FROM InvoiceDetail WHERE invoiceId = @invoiceId)
	UPDATE Invoice SET total = @total WHERE id = @invoiceId
	Declare @invoiceType INT
	SET @invoiceType = (SELECT [dbo].Invoice.[type] FROM Invoice WHERE id = @invoiceId)
	IF @invoiceType = 1
	   UPDATE Product SET stock = stock - (SELECT quantity FROM inserted) WHERE id = (SELECT productId FROM inserted)
	ELSE
	   UPDATE Product SET stock = stock + (SELECT quantity FROM inserted) WHERE id = (SELECT productId FROM inserted)
END
GO
