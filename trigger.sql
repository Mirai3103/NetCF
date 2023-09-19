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


create TRIGGER [trg_INVOICEDETAIL_INSERT] ON [InvoiceDetail]
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
	if(@invoiceType = 1)
	begin
		update Session set serviceCost = @total where computerID = (SELECT computerID FROM inserted)
	end
END
GO
