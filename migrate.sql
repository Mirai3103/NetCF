CREATE Database NetCF
Go
USE NetCF
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[balance] [float] NOT NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[deletedAt] [datetime2](7) NULL,
	[password] [varchar](45) NOT NULL,
	[role] [int] NULL,
	[username] [varchar](45) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Computer]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Computer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[deletedAt] [datetime2](7) NULL,
	[name] [varchar](100) NOT NULL,
	[price] [float] NOT NULL,
	[status] [int] NULL,
	[type] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ComputerUsage]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ComputerUsage](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[computerID] [int] NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[endAt] [datetime2](7) NULL,
	[isEmployeeUsing] [bit] NOT NULL,
	[totalMoney] [float] NULL,
	[usedByAccountId] [int] NULL,
	[usedBy] [int] NULL,
 CONSTRAINT [PK__Computer__3213E83FC8E93F82] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[deletedAt] [datetime2](7) NULL,
	[name] [nvarchar](100) NOT NULL,
	[otherInformation] [nvarchar](255) NULL,
	[salaryPerHour] [int] NULL,
	[phoneNumber] [char](15) NULL,
	[address] [nvarchar](250) NULL,
 CONSTRAINT [PK__Employee__3213E83F302F774D] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[computerId] [int] NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[createdBy] [int] NOT NULL,
	[createdToAccountId] [int] NULL,
	[deletedAt] [datetime2](7) NULL,
	[isPaid] [bit] NOT NULL,
	[note] [text] NULL,
	[status] [int] NOT NULL,
	[total] [float] NULL,
	[type] [int] NOT NULL,
 CONSTRAINT [PK__Invoice__3213E83F58DFCC58] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InvoiceDetail]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceId] [int] NOT NULL,
	[price] [float] NOT NULL,
	[productId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Message]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sessionId] [int] NOT NULL,
	[content] [nvarchar](255) NULL,
	[fromType] [int] NULL,
	[createdAt] [datetime2](7) NULL,
 CONSTRAINT [PK__Message__3213E83F731F660F] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime2](7) NOT NULL,
	[deletedAt] [datetime2](7) NULL,
	[description] [ntext] NOT NULL,
	[image] [nvarchar](255) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[price] [float] NOT NULL,
	[stock] [int] NOT NULL,
	[type] [int] NULL,
 CONSTRAINT [PK__Product__3213E83F4D1FA368] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 4/25/2023 8:29:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[computerID] [int] NOT NULL,
	[prepaidAmount] [float] NULL,
	[serviceCost] [int] NOT NULL,
	[startTime] [datetime2](7) NOT NULL,
	[totalTime] [int] NULL,
	[usedCost] [int] NOT NULL,
	[usedTime] [int] NOT NULL,
	[usingBy] [int] NULL,
 CONSTRAINT [PK__Session__3213E83FFAE34045] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (1, 0, CAST(N'2023-03-10T00:00:00.0000000' AS DateTime2), NULL, N'admin', 0, N'admin')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (2, 0, CAST(N'2023-03-10T00:00:00.0000000' AS DateTime2), NULL, N'manager', 1, N'manager')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (3, 0, CAST(N'2023-03-10T00:00:00.0000000' AS DateTime2), NULL, N'123456', 2, N'employee1')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (4, 0, CAST(N'2023-03-10T15:25:30.3660000' AS DateTime2), NULL, N'employee2', 2, N'employee2')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (5, 0, CAST(N'2023-03-10T15:25:30.3690000' AS DateTime2), NULL, N'employee3', 2, N'employee3')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (6, 0, CAST(N'2023-03-10T15:25:30.3730000' AS DateTime2), NULL, N'employee4', 2, N'employee4')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (7, 0, CAST(N'2023-03-10T15:25:30.3760000' AS DateTime2), NULL, N'employee5', 2, N'employee5')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (8, 15000, CAST(N'2023-01-06T20:45:37.0000000' AS DateTime2), NULL, N'm49UJn9', 3, N'cdeathridge0')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (9, 31000, CAST(N'2022-08-17T15:47:47.0000000' AS DateTime2), NULL, N'runadg', 3, N'cardley1')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (10, 21000, CAST(N'2022-09-07T01:00:51.0000000' AS DateTime2), NULL, N'TzOYqofp', 3, N'cslaght2')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (11, 2000, CAST(N'2022-09-29T00:00:00.0000000' AS DateTime2), NULL, N'123456', 3, N'hoagxxll')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (12, 34167, CAST(N'2022-07-05T00:00:00.0000000' AS DateTime2), NULL, N'123456', 3, N'lmtv')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (13, 14000, CAST(N'2022-10-19T07:40:35.0000000' AS DateTime2), NULL, N'123456', 3, N'du')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (14, 74000, CAST(N'2022-08-14T05:08:46.0000000' AS DateTime2), NULL, N'123456', 3, N'quan')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (15, 79000, CAST(N'2022-09-27T19:08:26.0000000' AS DateTime2), NULL, N'TOyjZqxf6g', 3, N'sjaques7')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (16, 18000, CAST(N'2022-02-23T09:15:06.0000000' AS DateTime2), NULL, N'zSr7ABG27', 3, N'mstennings8')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (17, 71000, CAST(N'2022-04-28T17:02:45.0000000' AS DateTime2), NULL, N'L4ZmP8oaq5ke', 3, N'ddecourcy9')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (18, 67000, CAST(N'2022-08-25T19:05:00.0000000' AS DateTime2), NULL, N'WKyZ5Q5p', 3, N'acastarda')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (19, 13000, CAST(N'2022-09-23T14:38:59.0000000' AS DateTime2), NULL, N'5tmHvA3', 3, N'sbraybrookeb')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (20, 17000, CAST(N'2022-03-18T08:31:14.0000000' AS DateTime2), NULL, N'75b0iu5', 3, N'ebillinghamc')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (21, 47000, CAST(N'2022-12-27T14:32:13.0000000' AS DateTime2), NULL, N'oG51V2emW', 3, N'efreestoned')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (22, 40000, CAST(N'2022-07-19T17:14:55.0000000' AS DateTime2), NULL, N'Sn1LhUbqS', 3, N'lwrothe')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (23, 53000, CAST(N'2022-12-11T16:55:36.0000000' AS DateTime2), NULL, N'C4j3o1csf', 3, N'aludfordf')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (24, 85000, CAST(N'2022-09-20T22:24:22.0000000' AS DateTime2), NULL, N'zCxhMm', 3, N'lboxhallg')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (25, 1000, CAST(N'2022-06-02T09:39:26.0000000' AS DateTime2), NULL, N'e3mA6Ylv', 3, N'nduffinh')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (26, 26000, CAST(N'2022-07-22T11:43:59.0000000' AS DateTime2), NULL, N'8pFsFS1Db', 3, N'agarlingi')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (27, 72000, CAST(N'2023-01-18T08:50:57.0000000' AS DateTime2), NULL, N'GTbNVIWu2LS', 3, N'bspawforthj')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (28, 100000, CAST(N'2022-05-04T02:00:59.0000000' AS DateTime2), NULL, N'Nvo6GiMg', 3, N'edickerk')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (29, 8000, CAST(N'2022-08-09T04:08:25.0000000' AS DateTime2), NULL, N'PS0boVdo', 3, N'cpuntl')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (30, 7000, CAST(N'2022-04-26T23:22:21.0000000' AS DateTime2), NULL, N'lcblEtkD', 3, N'ddudderidgem')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (31, 27000, CAST(N'2022-02-26T07:24:25.0000000' AS DateTime2), NULL, N'6h9nVXkqVfh', 3, N'ccampon')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (32, 80000, CAST(N'2022-09-23T17:01:10.0000000' AS DateTime2), NULL, N'dgM0xO', 3, N'mchampiono')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (33, 42000, CAST(N'2022-12-13T06:37:09.0000000' AS DateTime2), NULL, N'PzZ04v', 3, N'rmatchamp')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (34, 85000, CAST(N'2022-08-12T21:04:08.0000000' AS DateTime2), NULL, N'wbXAw7j', 3, N'awilloxq')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (35, 5000, CAST(N'2022-04-21T16:28:51.0000000' AS DateTime2), NULL, N'meqs1M', 3, N'sluetyr')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (36, 58000, CAST(N'2022-02-14T16:41:48.0000000' AS DateTime2), NULL, N'1DvCi7Jd', 3, N'cbenettinis')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (37, 11000, CAST(N'2022-11-18T10:40:31.0000000' AS DateTime2), NULL, N'bXXO3Khxe', 3, N'bswordert')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (38, 76000, CAST(N'2022-04-26T18:42:57.0000000' AS DateTime2), NULL, N'W10hX68MiyoH', 3, N'ghalletu')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (39, 62000, CAST(N'2022-05-18T23:32:59.0000000' AS DateTime2), NULL, N'ZPygav0R', 3, N'aglencrossv')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (40, 43000, CAST(N'2022-09-29T07:50:38.0000000' AS DateTime2), NULL, N'CdzrGpNPZq4Y', 3, N'cmundwellw')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (41, 50000, CAST(N'2022-09-26T01:10:04.0000000' AS DateTime2), NULL, N'dkFaHCgC', 3, N'bkubicekx')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (42, 98000, CAST(N'2022-10-16T15:53:22.0000000' AS DateTime2), NULL, N'HrCSIq', 3, N'wscatchery')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (43, 13000, CAST(N'2022-04-12T23:50:32.0000000' AS DateTime2), NULL, N'NziIQTWt', 3, N'kfuxmanz')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (44, 0, CAST(N'2022-11-24T15:03:52.0000000' AS DateTime2), NULL, N'Aqw5TIh7Ke', 3, N'lharrild10')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (45, 96000, CAST(N'2022-12-22T21:24:31.0000000' AS DateTime2), NULL, N'zO07EiM9O3', 3, N'lhellyar11')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (46, 66000, CAST(N'2022-09-28T22:23:46.0000000' AS DateTime2), NULL, N'L1tuknBT0', 3, N'cwoodroofe12')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (47, 58000, CAST(N'2022-06-04T05:47:02.0000000' AS DateTime2), NULL, N'QPNiPT3d', 3, N'sjohnsson13')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (48, 21000, CAST(N'2022-02-01T03:54:06.0000000' AS DateTime2), NULL, N'cRWaA7yqNLN', 3, N'wcrayk14')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (49, 72000, CAST(N'2022-10-02T21:14:05.0000000' AS DateTime2), NULL, N'Lki7ot2c0', 3, N'aainscow15')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (50, 94000, CAST(N'2022-04-18T04:51:56.0000000' AS DateTime2), NULL, N'j2EKs4ztNzZE', 3, N'etelezhkin16')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (51, 87000, CAST(N'2022-12-10T18:05:10.0000000' AS DateTime2), NULL, N'ONOJX32zK', 3, N'nochterlonie17')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (52, 73000, CAST(N'2022-10-01T06:49:47.0000000' AS DateTime2), NULL, N'gaJwB9z', 3, N'ppanchen18')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (53, 49000, CAST(N'2022-03-24T02:18:40.0000000' AS DateTime2), NULL, N'uBhIcPVo', 3, N'bpiscopello19')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (54, 55000, CAST(N'2022-03-29T18:41:19.0000000' AS DateTime2), NULL, N'gZttV0R', 3, N'rwebermann1a')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (55, 91000, CAST(N'2022-11-13T00:36:31.0000000' AS DateTime2), NULL, N'bblVIR0vOa10', 3, N'sbrahmer1b')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (56, 100000, CAST(N'2022-04-06T01:10:09.0000000' AS DateTime2), NULL, N'O97DnN', 3, N'ifeedome1c')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (57, 100000, CAST(N'2022-11-11T23:26:20.0000000' AS DateTime2), NULL, N'ZfINpA5mF8xS', 3, N'egiven1d')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (58, 54000, CAST(N'2022-11-19T08:48:29.0000000' AS DateTime2), NULL, N'yNQHWt5VPdRc', 3, N'dblaxlande1e')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (59, 18000, CAST(N'2022-06-02T06:26:27.0000000' AS DateTime2), NULL, N'oFzk0gSv3LZ', 3, N'gnairn1f')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (60, 16000, CAST(N'2022-11-05T12:56:11.0000000' AS DateTime2), NULL, N'OmYaxSnuhpFr', 3, N'mlattimore1g')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (61, 57000, CAST(N'2022-06-09T11:04:01.0000000' AS DateTime2), NULL, N'DMtRWUSBVmlz', 3, N'blaying1h')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (62, 48000, CAST(N'2022-04-24T16:21:42.0000000' AS DateTime2), NULL, N'42yMzq', 3, N'fhyde1i')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (63, 58000, CAST(N'2022-04-17T20:25:44.0000000' AS DateTime2), NULL, N'nnd3ib8kZV', 3, N'wmaccarlich1j')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (64, 29000, CAST(N'2022-09-04T17:12:49.0000000' AS DateTime2), NULL, N'kO0Q70JUg', 3, N'tallery1k')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (65, 47000, CAST(N'2022-09-25T05:49:39.0000000' AS DateTime2), NULL, N'PFlquw5gN', 3, N'lshepard1l')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (66, 15000, CAST(N'2022-08-13T22:57:05.0000000' AS DateTime2), NULL, N'pNJMuvkf', 3, N'jposkitt1m')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (67, 68000, CAST(N'2022-12-21T06:36:50.0000000' AS DateTime2), NULL, N'eFTO4PycAv6p', 3, N'dsteggals1n')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (68, 72000, CAST(N'2023-01-14T15:02:10.0000000' AS DateTime2), NULL, N'saGT9tfyE', 3, N'bsumbler1o')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (69, 50000, CAST(N'2023-02-07T06:25:32.0000000' AS DateTime2), NULL, N'SzZumAP', 3, N'dringham1p')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (70, 21000, CAST(N'2022-04-13T08:35:32.0000000' AS DateTime2), NULL, N'0XiaVsVg', 3, N'nhardway1q')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (71, 45000, CAST(N'2022-07-02T23:52:12.0000000' AS DateTime2), NULL, N'O1cefz', 3, N'sgartan1r')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (72, 84000, CAST(N'2023-02-18T18:48:46.0000000' AS DateTime2), NULL, N'HOU22M4oF', 3, N'mcrocumbe1s')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (73, 78000, CAST(N'2022-12-26T05:04:46.0000000' AS DateTime2), NULL, N'neK2Ir2p', 3, N'gbrimley1t')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (74, 8000, CAST(N'2022-04-30T00:13:21.0000000' AS DateTime2), NULL, N'ou0weeKuHJFM', 3, N'wgaul1u')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (75, 3000, CAST(N'2022-10-28T04:08:41.0000000' AS DateTime2), NULL, N'IrT8Fmp', 3, N'mhughf1v')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (76, 35000, CAST(N'2023-01-16T07:24:22.0000000' AS DateTime2), NULL, N'Z49RB7HVyv', 3, N'babrahams1w')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (77, 39000, CAST(N'2022-07-21T05:50:24.0000000' AS DateTime2), NULL, N'bfmNuq', 3, N'dvandevelde1x')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (78, 2000, CAST(N'2022-12-31T18:49:54.0000000' AS DateTime2), NULL, N'xQ9j8MRMf', 3, N'lgoodnow1y')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (79, 26000, CAST(N'2022-09-08T18:34:49.0000000' AS DateTime2), NULL, N'3f0nSaUe6', 3, N'inoon1z')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (80, 14000, CAST(N'2022-09-30T08:17:28.0000000' AS DateTime2), NULL, N'o5hz7j0', 3, N'sllewhellin20')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (81, 26000, CAST(N'2022-02-06T21:00:09.0000000' AS DateTime2), NULL, N'7cqNGJmjmzp7', 3, N'espeir21')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (82, 28000, CAST(N'2022-10-09T14:56:19.0000000' AS DateTime2), NULL, N'1MxRUmUwUg9', 3, N'cglanville22')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (83, 32000, CAST(N'2022-06-04T15:18:59.0000000' AS DateTime2), NULL, N'jzy0DJ1jTK', 3, N'fsavil23')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (84, 24000, CAST(N'2022-04-04T03:11:17.0000000' AS DateTime2), NULL, N'aGwh2EjrApzS', 3, N'klivings24')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (85, 40000, CAST(N'2022-12-29T10:04:21.0000000' AS DateTime2), NULL, N'IGsL0w', 3, N'cfrunks25')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (86, 78000, CAST(N'2022-05-16T04:40:52.0000000' AS DateTime2), NULL, N'lfxi2APtJ3M', 3, N'glightbody26')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (87, 53000, CAST(N'2022-09-01T06:12:24.0000000' AS DateTime2), NULL, N'QmUkmps', 3, N'acaveill27')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (88, 31000, CAST(N'2023-01-13T15:17:53.0000000' AS DateTime2), NULL, N'h3rC6HgBQ3i', 3, N'afuentes28')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (89, 76000, CAST(N'2022-02-09T22:16:36.0000000' AS DateTime2), NULL, N'wy4f0j3E', 3, N'bloving29')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (90, 16000, CAST(N'2022-06-10T22:38:38.0000000' AS DateTime2), NULL, N'yOBpH1Aqwwa', 3, N'lcowdrey2a')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (91, 67000, CAST(N'2022-10-16T22:24:37.0000000' AS DateTime2), NULL, N'kYLntGe', 3, N'rshale2b')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (92, 88000, CAST(N'2022-08-19T10:50:33.0000000' AS DateTime2), NULL, N'zCItiOf', 3, N'dladbury2c')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (93, 36000, CAST(N'2022-12-26T17:46:48.0000000' AS DateTime2), NULL, N'99h5zwL', 3, N'tscandred2d')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (94, 9000, CAST(N'2022-04-04T12:42:03.0000000' AS DateTime2), NULL, N'GGg1ut', 3, N'fpickover2e')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (95, 78000, CAST(N'2022-11-08T00:00:00.0000000' AS DateTime2), NULL, N'nxURZagT', 3, N'btomlins2f')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (96, 25000, CAST(N'2022-07-11T00:37:42.0000000' AS DateTime2), NULL, N'OkW4A5fqka', 3, N'brichemont2g')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (97, 8000, CAST(N'2022-02-16T06:55:57.0000000' AS DateTime2), NULL, N'IHSX3epB1V', 3, N'claister2h')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (98, 36000, CAST(N'2022-08-09T23:55:56.0000000' AS DateTime2), NULL, N'gKr1sdT8VYQ', 3, N'rdeniset2i')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (99, 39000, CAST(N'2022-11-20T12:20:02.0000000' AS DateTime2), NULL, N'I9ixlRmHwL33', 3, N'vmedhurst2j')
GO
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (100, 70000, CAST(N'2022-12-02T15:12:13.0000000' AS DateTime2), NULL, N'NuTJWao', 3, N'habden2k')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (101, 48000, CAST(N'2022-09-30T14:32:34.0000000' AS DateTime2), NULL, N'bhjcViY6N', 3, N'kwestphal2l')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (102, 61000, CAST(N'2022-11-12T23:52:59.0000000' AS DateTime2), NULL, N'NtaLBHtjjaY', 3, N'mcullen2m')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (103, 30000, CAST(N'2023-01-17T05:32:46.0000000' AS DateTime2), NULL, N'Gv80OUlb', 3, N'lbucklee2n')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (104, 11000, CAST(N'2022-11-18T23:39:01.0000000' AS DateTime2), NULL, N'OBToEZjF9iiK', 3, N'nleyton2o')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (105, 90000, CAST(N'2022-12-14T12:13:39.0000000' AS DateTime2), NULL, N'tArfQfRAH', 3, N'vattaway2p')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (106, 30000, CAST(N'2022-06-06T03:24:36.0000000' AS DateTime2), NULL, N'oJAEiI6YpbYQ', 3, N'lholyland2q')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (107, 47000, CAST(N'2022-04-04T10:40:38.0000000' AS DateTime2), NULL, N'vTQlvMnwzrY', 3, N'hballeine2r')
INSERT [dbo].[Account] ([id], [balance], [createdAt], [deletedAt], [password], [role], [username]) VALUES (108, 40000, CAST(N'2023-03-27T00:00:00.0000000' AS DateTime2), NULL, N'123456', 3, N'123456')
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Computer] ON 

INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (1, CAST(N'2023-03-10T15:25:30.3980000' AS DateTime2), NULL, N'Máy 1', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (2, CAST(N'2023-03-10T15:25:30.4040000' AS DateTime2), NULL, N'Máy 2', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (3, CAST(N'2023-03-10T15:25:30.4060000' AS DateTime2), NULL, N'Máy 3', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (4, CAST(N'2023-03-10T15:25:30.4070000' AS DateTime2), NULL, N'Máy 4', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (5, CAST(N'2023-03-10T15:25:30.4080000' AS DateTime2), NULL, N'Máy 5', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (6, CAST(N'2023-03-10T15:25:30.4090000' AS DateTime2), NULL, N'Máy 6', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (7, CAST(N'2023-03-10T15:25:30.4110000' AS DateTime2), NULL, N'Máy 7', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (8, CAST(N'2023-03-10T15:25:30.4120000' AS DateTime2), NULL, N'Máy 8', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (9, CAST(N'2023-03-10T15:25:30.4140000' AS DateTime2), NULL, N'Máy 9', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (10, CAST(N'2023-03-10T15:25:30.4150000' AS DateTime2), NULL, N'Máy 10', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (11, CAST(N'2023-03-10T15:25:30.4170000' AS DateTime2), NULL, N'Máy 11', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (12, CAST(N'2023-03-10T15:25:30.4180000' AS DateTime2), NULL, N'Máy 12', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (13, CAST(N'2023-03-10T15:25:30.4200000' AS DateTime2), NULL, N'Máy 13', 5000, 2, 1)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (14, CAST(N'2023-03-10T15:25:30.4220000' AS DateTime2), NULL, N'Máy 14', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (15, CAST(N'2023-03-10T15:25:30.4240000' AS DateTime2), NULL, N'Máy 15', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (16, CAST(N'2023-03-10T15:25:30.4250000' AS DateTime2), NULL, N'Máy 16', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (17, CAST(N'2023-03-10T15:25:30.4270000' AS DateTime2), NULL, N'Máy 17', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (18, CAST(N'2023-03-10T15:25:30.4280000' AS DateTime2), NULL, N'Máy 18', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (19, CAST(N'2023-03-10T15:25:30.4290000' AS DateTime2), NULL, N'Máy 19', 10000, 2, 0)
INSERT [dbo].[Computer] ([id], [createdAt], [deletedAt], [name], [price], [status], [type]) VALUES (20, CAST(N'2023-03-10T15:25:30.4300000' AS DateTime2), NULL, N'Máy 20', 5000, 2, 1)
SET IDENTITY_INSERT [dbo].[Computer] OFF
GO
SET IDENTITY_INSERT [dbo].[ComputerUsage] ON 

INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (1, 20, CAST(N'2021-07-25T05:24:58.6430000' AS DateTime2), CAST(N'2021-07-25T10:24:58.6430000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (2, 10, CAST(N'2021-11-19T20:32:10.4320000' AS DateTime2), CAST(N'2021-11-19T22:56:10.4320000' AS DateTime2), 0, 12000, 15, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (3, 6, CAST(N'2021-08-06T06:01:56.3750000' AS DateTime2), CAST(N'2021-08-06T08:07:56.3750000' AS DateTime2), 0, 21000, 67, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (4, 9, CAST(N'2022-11-16T06:18:13.1400000' AS DateTime2), CAST(N'2022-11-16T10:42:13.1400000' AS DateTime2), 0, 22000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (5, 7, CAST(N'2022-02-22T07:11:16.5690000' AS DateTime2), CAST(N'2022-02-22T08:53:16.5690000' AS DateTime2), 0, 17000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (6, 18, CAST(N'2021-05-06T19:22:09.5710000' AS DateTime2), CAST(N'2021-05-06T21:04:09.5710000' AS DateTime2), 0, 17000, 31, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (7, 20, CAST(N'2021-03-01T11:56:29.3720000' AS DateTime2), CAST(N'2021-03-01T16:32:29.3720000' AS DateTime2), 0, 23000, 39, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (8, 16, CAST(N'2022-11-22T01:57:40.6710000' AS DateTime2), CAST(N'2022-11-22T03:51:40.6710000' AS DateTime2), 0, 19000, 97, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (9, 16, CAST(N'2023-02-13T16:42:19.2070000' AS DateTime2), CAST(N'2023-02-13T17:12:19.2070000' AS DateTime2), 0, 5000, 39, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (10, 19, CAST(N'2021-07-10T16:00:34.9570000' AS DateTime2), CAST(N'2021-07-10T18:24:34.9570000' AS DateTime2), 0, 24000, 56, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (11, 13, CAST(N'2021-12-23T23:08:39.5030000' AS DateTime2), CAST(N'2021-12-24T01:44:39.5030000' AS DateTime2), 0, 13000, 56, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (12, 16, CAST(N'2022-11-18T06:29:00.6760000' AS DateTime2), CAST(N'2022-11-18T08:11:00.6760000' AS DateTime2), 0, 17000, 93, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (13, 2, CAST(N'2022-02-25T21:14:52.5990000' AS DateTime2), CAST(N'2022-02-25T22:56:52.5990000' AS DateTime2), 0, 17000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (14, 1, CAST(N'2022-06-05T02:25:16.0910000' AS DateTime2), CAST(N'2022-06-05T06:25:16.0910000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (15, 9, CAST(N'2021-04-09T02:27:30.3310000' AS DateTime2), CAST(N'2021-04-09T04:03:30.3310000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (16, 5, CAST(N'2023-01-08T10:05:30.2390000' AS DateTime2), CAST(N'2023-01-08T13:41:30.2390000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (17, 15, CAST(N'2022-12-24T19:52:20.6340000' AS DateTime2), CAST(N'2022-12-24T21:52:20.6340000' AS DateTime2), 0, 20000, 33, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (18, 7, CAST(N'2021-09-12T12:08:27.0900000' AS DateTime2), CAST(N'2021-09-12T13:08:27.0900000' AS DateTime2), 0, 10000, 93, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (19, 2, CAST(N'2023-01-21T06:19:01.4680000' AS DateTime2), CAST(N'2023-01-21T07:31:01.4680000' AS DateTime2), 0, 12000, 64, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (20, 16, CAST(N'2023-02-08T15:03:20.8980000' AS DateTime2), CAST(N'2023-02-08T15:39:20.8980000' AS DateTime2), 0, 6000, 27, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (21, 15, CAST(N'2021-07-18T14:14:05.6300000' AS DateTime2), CAST(N'2021-07-18T15:08:05.6300000' AS DateTime2), 0, 9000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (22, 6, CAST(N'2022-06-22T21:49:25.9390000' AS DateTime2), CAST(N'2022-06-23T00:01:25.9390000' AS DateTime2), 0, 22000, 72, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (23, 13, CAST(N'2021-08-18T16:58:53.7620000' AS DateTime2), CAST(N'2021-08-18T21:46:53.7620000' AS DateTime2), 0, 24000, 77, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (24, 12, CAST(N'2022-08-01T09:52:36.2780000' AS DateTime2), CAST(N'2022-08-01T10:40:36.2780000' AS DateTime2), 0, 8000, 50, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (25, 12, CAST(N'2022-09-30T22:10:14.9840000' AS DateTime2), CAST(N'2022-10-01T00:28:14.9840000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (26, 12, CAST(N'2021-04-29T09:05:04.5990000' AS DateTime2), CAST(N'2021-04-29T10:05:04.5990000' AS DateTime2), 0, 10000, 103, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (27, 16, CAST(N'2021-11-08T07:25:43.5490000' AS DateTime2), CAST(N'2021-11-08T09:37:43.5490000' AS DateTime2), 0, 22000, 68, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (28, 1, CAST(N'2021-03-26T17:38:57.2900000' AS DateTime2), CAST(N'2021-03-26T19:26:57.2900000' AS DateTime2), 0, 9000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (29, 11, CAST(N'2022-01-06T23:09:06.5850000' AS DateTime2), CAST(N'2022-01-07T02:09:06.5850000' AS DateTime2), 0, 15000, 44, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (30, 9, CAST(N'2022-11-14T09:34:01.7550000' AS DateTime2), CAST(N'2022-11-14T11:46:01.7550000' AS DateTime2), 0, 11000, 43, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (31, 4, CAST(N'2022-06-07T03:33:21.0040000' AS DateTime2), CAST(N'2022-06-07T05:45:21.0040000' AS DateTime2), 0, 11000, 49, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (32, 8, CAST(N'2022-08-05T22:01:06.2780000' AS DateTime2), CAST(N'2022-08-05T23:49:06.2780000' AS DateTime2), 0, 18000, 8, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (33, 12, CAST(N'2021-09-23T04:28:00.8850000' AS DateTime2), CAST(N'2021-09-23T06:40:00.8850000' AS DateTime2), 0, 22000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (34, 13, CAST(N'2021-09-23T18:39:20.7050000' AS DateTime2), CAST(N'2021-09-23T21:27:20.7050000' AS DateTime2), 0, 14000, 101, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (35, 11, CAST(N'2023-01-23T21:04:55.0890000' AS DateTime2), CAST(N'2023-01-23T22:16:55.0890000' AS DateTime2), 0, 6000, 86, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (36, 7, CAST(N'2021-11-25T00:44:37.6970000' AS DateTime2), CAST(N'2021-11-25T02:02:37.6970000' AS DateTime2), 0, 13000, 77, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (37, 11, CAST(N'2022-10-12T09:13:18.3180000' AS DateTime2), CAST(N'2022-10-12T12:37:18.3180000' AS DateTime2), 0, 17000, 26, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (38, 12, CAST(N'2021-07-24T11:06:54.0840000' AS DateTime2), CAST(N'2021-07-24T12:12:54.0840000' AS DateTime2), 0, 11000, 106, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (39, 14, CAST(N'2021-11-22T23:16:06.3430000' AS DateTime2), CAST(N'2021-11-23T00:04:06.3430000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (40, 8, CAST(N'2022-07-14T06:44:22.7200000' AS DateTime2), CAST(N'2022-07-14T09:14:22.7200000' AS DateTime2), 0, 25000, 49, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (41, 11, CAST(N'2022-11-20T11:41:59.3350000' AS DateTime2), CAST(N'2022-11-20T15:29:59.3350000' AS DateTime2), 0, 19000, 75, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (42, 9, CAST(N'2021-04-14T13:43:21.6090000' AS DateTime2), CAST(N'2021-04-14T18:07:21.6090000' AS DateTime2), 0, 22000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (43, 5, CAST(N'2022-04-30T23:46:55.8610000' AS DateTime2), CAST(N'2022-05-01T04:10:55.8610000' AS DateTime2), 0, 22000, 98, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (44, 3, CAST(N'2022-02-27T18:46:33.5390000' AS DateTime2), CAST(N'2022-02-27T21:10:33.5390000' AS DateTime2), 0, 12000, 63, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (45, 2, CAST(N'2021-03-09T21:47:51.2510000' AS DateTime2), CAST(N'2021-03-09T23:47:51.2510000' AS DateTime2), 0, 20000, 99, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (46, 7, CAST(N'2022-01-04T01:11:55.0210000' AS DateTime2), CAST(N'2022-01-04T01:53:55.0210000' AS DateTime2), 0, 7000, 54, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (47, 1, CAST(N'2021-03-08T12:50:15.2990000' AS DateTime2), CAST(N'2021-03-08T15:26:15.2990000' AS DateTime2), 0, 13000, 11, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (48, 13, CAST(N'2021-11-14T12:12:34.7490000' AS DateTime2), CAST(N'2021-11-14T16:12:34.7490000' AS DateTime2), 0, 20000, 33, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (49, 18, CAST(N'2022-05-13T04:35:03.7190000' AS DateTime2), CAST(N'2022-05-13T06:53:03.7190000' AS DateTime2), 0, 23000, 52, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (50, 6, CAST(N'2023-01-24T00:39:51.0530000' AS DateTime2), CAST(N'2023-01-24T02:15:51.0530000' AS DateTime2), 0, 16000, 47, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (51, 7, CAST(N'2021-09-02T00:59:52.5940000' AS DateTime2), CAST(N'2021-09-02T03:23:52.5940000' AS DateTime2), 0, 24000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (52, 20, CAST(N'2021-07-27T14:45:23.6980000' AS DateTime2), CAST(N'2021-07-27T17:33:23.6980000' AS DateTime2), 0, 14000, 48, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (53, 12, CAST(N'2021-07-06T23:54:38.9750000' AS DateTime2), CAST(N'2021-07-07T00:36:38.9750000' AS DateTime2), 0, 7000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (54, 9, CAST(N'2021-03-19T04:04:58.5130000' AS DateTime2), CAST(N'2021-03-19T05:04:58.5130000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (55, 20, CAST(N'2022-08-18T03:15:07.9660000' AS DateTime2), CAST(N'2022-08-18T07:03:07.9660000' AS DateTime2), 0, 19000, 107, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (56, 11, CAST(N'2022-02-22T06:29:27.2680000' AS DateTime2), CAST(N'2022-02-22T11:29:27.2680000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (57, 1, CAST(N'2022-02-11T09:05:07.1440000' AS DateTime2), CAST(N'2022-02-11T12:05:07.1440000' AS DateTime2), 0, 15000, 73, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (58, 7, CAST(N'2022-07-30T20:06:12.7010000' AS DateTime2), CAST(N'2022-07-30T21:18:12.7010000' AS DateTime2), 0, 12000, 105, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (59, 18, CAST(N'2022-07-20T17:09:22.6940000' AS DateTime2), CAST(N'2022-07-20T18:27:22.6940000' AS DateTime2), 0, 13000, 20, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (60, 7, CAST(N'2022-10-20T02:25:01.9410000' AS DateTime2), CAST(N'2022-10-20T04:25:01.9410000' AS DateTime2), 0, 20000, 46, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (61, 5, CAST(N'2022-04-03T12:15:09.2000000' AS DateTime2), CAST(N'2022-04-03T14:39:09.2000000' AS DateTime2), 0, 12000, 74, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (62, 4, CAST(N'2021-02-15T17:53:30.5290000' AS DateTime2), CAST(N'2021-02-15T21:17:30.5290000' AS DateTime2), 0, 17000, 17, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (63, 15, CAST(N'2022-05-16T19:06:31.7620000' AS DateTime2), CAST(N'2022-05-16T21:00:31.7620000' AS DateTime2), 0, 19000, 92, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (64, 5, CAST(N'2022-08-01T10:04:36.2320000' AS DateTime2), CAST(N'2022-08-01T12:28:36.2320000' AS DateTime2), 0, 12000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (65, 11, CAST(N'2021-11-03T19:07:50.6050000' AS DateTime2), CAST(N'2021-11-03T21:31:50.6050000' AS DateTime2), 0, 12000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (66, 3, CAST(N'2021-03-15T20:15:40.2770000' AS DateTime2), CAST(N'2021-03-15T23:15:40.2770000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (67, 6, CAST(N'2022-05-10T03:23:17.8420000' AS DateTime2), CAST(N'2022-05-10T05:53:17.8420000' AS DateTime2), 0, 25000, 42, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (68, 7, CAST(N'2021-06-14T17:47:14.8440000' AS DateTime2), CAST(N'2021-06-14T19:11:14.8440000' AS DateTime2), 0, 14000, 107, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (69, 10, CAST(N'2023-01-16T09:04:42.1140000' AS DateTime2), CAST(N'2023-01-16T13:04:42.1140000' AS DateTime2), 0, 20000, 104, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (70, 14, CAST(N'2022-02-10T17:40:12.9560000' AS DateTime2), CAST(N'2022-02-10T19:28:12.9560000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (71, 17, CAST(N'2022-05-07T07:19:20.4440000' AS DateTime2), CAST(N'2022-05-07T09:43:20.4440000' AS DateTime2), 0, 24000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (72, 5, CAST(N'2022-10-30T06:14:23.3110000' AS DateTime2), CAST(N'2022-10-30T08:38:23.3110000' AS DateTime2), 0, 12000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (73, 14, CAST(N'2021-03-18T04:14:02.3620000' AS DateTime2), CAST(N'2021-03-18T06:32:02.3620000' AS DateTime2), 0, 23000, 23, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (74, 4, CAST(N'2022-09-28T15:47:04.6280000' AS DateTime2), CAST(N'2022-09-28T19:59:04.6280000' AS DateTime2), 0, 21000, 48, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (75, 6, CAST(N'2022-04-01T03:50:58.5580000' AS DateTime2), CAST(N'2022-04-01T06:20:58.5580000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (76, 5, CAST(N'2021-02-24T13:06:34.2840000' AS DateTime2), CAST(N'2021-02-24T16:54:34.2840000' AS DateTime2), 0, 19000, 55, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (77, 14, CAST(N'2023-01-16T10:13:08.6680000' AS DateTime2), CAST(N'2023-01-16T12:19:08.6680000' AS DateTime2), 0, 21000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (78, 18, CAST(N'2022-11-02T12:24:34.4120000' AS DateTime2), CAST(N'2022-11-02T14:36:34.4120000' AS DateTime2), 0, 22000, 9, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (79, 8, CAST(N'2021-10-22T18:15:25.6820000' AS DateTime2), CAST(N'2021-10-22T19:03:25.6820000' AS DateTime2), 0, 8000, 77, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (80, 11, CAST(N'2022-10-28T00:47:44.1430000' AS DateTime2), CAST(N'2022-10-28T05:47:44.1430000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (81, 12, CAST(N'2023-01-09T22:35:27.0710000' AS DateTime2), CAST(N'2023-01-09T23:11:27.0710000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (82, 19, CAST(N'2022-01-08T03:00:45.3500000' AS DateTime2), CAST(N'2022-01-08T05:18:45.3500000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (83, 4, CAST(N'2022-08-07T21:35:24.9050000' AS DateTime2), CAST(N'2022-08-07T22:35:24.9050000' AS DateTime2), 0, 5000, 58, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (84, 8, CAST(N'2022-09-16T21:31:39.5850000' AS DateTime2), CAST(N'2022-09-17T00:01:39.5850000' AS DateTime2), 0, 25000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (85, 19, CAST(N'2022-09-17T23:58:10.6590000' AS DateTime2), CAST(N'2022-09-18T01:58:10.6590000' AS DateTime2), 0, 20000, 44, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (86, 15, CAST(N'2021-07-26T13:29:26.4820000' AS DateTime2), CAST(N'2021-07-26T14:17:26.4820000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (87, 7, CAST(N'2021-09-26T22:37:42.6270000' AS DateTime2), CAST(N'2021-09-27T00:01:42.6270000' AS DateTime2), 0, 14000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (88, 16, CAST(N'2023-03-04T09:30:01.4940000' AS DateTime2), CAST(N'2023-03-04T10:30:01.4940000' AS DateTime2), 0, 10000, 16, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (89, 1, CAST(N'2021-04-05T00:36:39.2960000' AS DateTime2), CAST(N'2021-04-05T02:48:39.2960000' AS DateTime2), 0, 11000, 94, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (90, 3, CAST(N'2022-09-23T08:38:49.2800000' AS DateTime2), CAST(N'2022-09-23T11:50:49.2800000' AS DateTime2), 0, 16000, 48, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (91, 18, CAST(N'2021-03-31T15:46:08.9980000' AS DateTime2), CAST(N'2021-03-31T17:28:08.9980000' AS DateTime2), 0, 17000, 98, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (92, 9, CAST(N'2021-06-17T17:43:16.5500000' AS DateTime2), CAST(N'2021-06-17T22:19:16.5500000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (93, 14, CAST(N'2021-08-16T07:42:49.7350000' AS DateTime2), CAST(N'2021-08-16T10:06:49.7350000' AS DateTime2), 0, 24000, 76, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (94, 6, CAST(N'2023-03-05T07:07:29.1740000' AS DateTime2), CAST(N'2023-03-05T09:37:29.1740000' AS DateTime2), 0, 25000, 39, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (95, 1, CAST(N'2021-04-11T08:24:56.6210000' AS DateTime2), CAST(N'2021-04-11T09:36:56.6210000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (96, 6, CAST(N'2021-11-30T05:50:48.0540000' AS DateTime2), CAST(N'2021-11-30T06:32:48.0540000' AS DateTime2), 0, 7000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (97, 18, CAST(N'2023-03-05T21:43:50.5090000' AS DateTime2), CAST(N'2023-03-05T22:19:50.5090000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (98, 15, CAST(N'2021-09-13T08:09:22.2760000' AS DateTime2), CAST(N'2021-09-13T09:03:22.2760000' AS DateTime2), 0, 9000, 84, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (99, 4, CAST(N'2023-01-27T17:05:20.9100000' AS DateTime2), CAST(N'2023-01-27T20:29:20.9100000' AS DateTime2), 0, 17000, 81, NULL)
GO
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (100, 15, CAST(N'2022-02-07T12:00:48.3910000' AS DateTime2), CAST(N'2022-02-07T14:24:48.3910000' AS DateTime2), 0, 24000, 91, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (101, 14, CAST(N'2022-03-05T01:59:38.6880000' AS DateTime2), CAST(N'2022-03-05T04:11:38.6880000' AS DateTime2), 0, 22000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (102, 11, CAST(N'2023-01-15T23:11:06.3840000' AS DateTime2), CAST(N'2023-01-16T02:11:06.3840000' AS DateTime2), 0, 15000, 30, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (103, 1, CAST(N'2022-10-30T18:30:20.0940000' AS DateTime2), CAST(N'2022-10-30T22:54:20.0940000' AS DateTime2), 0, 22000, 106, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (104, 6, CAST(N'2021-07-10T19:42:42.5790000' AS DateTime2), CAST(N'2021-07-10T21:00:42.5790000' AS DateTime2), 0, 13000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (105, 5, CAST(N'2022-10-01T05:02:52.5990000' AS DateTime2), CAST(N'2022-10-01T09:14:52.5990000' AS DateTime2), 0, 21000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (106, 1, CAST(N'2022-12-01T22:21:36.3420000' AS DateTime2), CAST(N'2022-12-02T00:09:36.3420000' AS DateTime2), 0, 9000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (107, 1, CAST(N'2021-05-22T19:46:44.3510000' AS DateTime2), CAST(N'2021-05-22T22:58:44.3510000' AS DateTime2), 0, 16000, 40, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (108, 7, CAST(N'2021-08-20T07:23:02.5110000' AS DateTime2), CAST(N'2021-08-20T07:53:02.5110000' AS DateTime2), 0, 5000, 62, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (109, 10, CAST(N'2022-05-17T04:27:45.4970000' AS DateTime2), CAST(N'2022-05-17T07:27:45.4970000' AS DateTime2), 0, 15000, 12, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (110, 1, CAST(N'2021-02-23T15:02:48.0780000' AS DateTime2), CAST(N'2021-02-23T19:26:48.0780000' AS DateTime2), 0, 22000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (111, 9, CAST(N'2021-12-23T02:15:05.4870000' AS DateTime2), CAST(N'2021-12-23T05:51:05.4870000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (112, 14, CAST(N'2022-04-01T10:21:10.1400000' AS DateTime2), CAST(N'2022-04-01T12:33:10.1400000' AS DateTime2), 0, 22000, 33, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (113, 18, CAST(N'2022-03-03T22:35:03.4310000' AS DateTime2), CAST(N'2022-03-04T00:41:03.4310000' AS DateTime2), 0, 21000, 83, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (114, 6, CAST(N'2022-04-04T02:14:40.5990000' AS DateTime2), CAST(N'2022-04-04T03:44:40.5990000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (115, 11, CAST(N'2021-12-15T03:21:03.3090000' AS DateTime2), CAST(N'2021-12-15T06:09:03.3090000' AS DateTime2), 0, 14000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (116, 20, CAST(N'2021-03-19T10:40:14.3370000' AS DateTime2), CAST(N'2021-03-19T14:04:14.3370000' AS DateTime2), 0, 17000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (117, 10, CAST(N'2023-02-04T07:23:04.8160000' AS DateTime2), CAST(N'2023-02-04T09:35:04.8160000' AS DateTime2), 0, 11000, 18, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (118, 17, CAST(N'2021-11-16T10:36:04.2520000' AS DateTime2), CAST(N'2021-11-16T12:30:04.2520000' AS DateTime2), 0, 19000, 64, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (119, 5, CAST(N'2022-10-23T14:56:06.4190000' AS DateTime2), CAST(N'2022-10-23T19:08:06.4190000' AS DateTime2), 0, 21000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (120, 3, CAST(N'2021-08-26T09:42:26.4450000' AS DateTime2), CAST(N'2021-08-26T12:54:26.4450000' AS DateTime2), 0, 16000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (121, 10, CAST(N'2022-04-03T10:35:51.2920000' AS DateTime2), CAST(N'2022-04-03T15:23:51.2920000' AS DateTime2), 0, 24000, 98, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (122, 20, CAST(N'2022-02-16T06:11:33.6710000' AS DateTime2), CAST(N'2022-02-16T10:35:33.6710000' AS DateTime2), 0, 22000, 104, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (123, 20, CAST(N'2021-08-11T00:04:28.0400000' AS DateTime2), CAST(N'2021-08-11T01:16:28.0400000' AS DateTime2), 0, 6000, 22, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (124, 13, CAST(N'2022-02-08T04:33:54.5620000' AS DateTime2), CAST(N'2022-02-08T08:45:54.5620000' AS DateTime2), 0, 21000, 84, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (125, 8, CAST(N'2022-08-23T19:30:26.4090000' AS DateTime2), CAST(N'2022-08-23T21:00:26.4090000' AS DateTime2), 0, 15000, 44, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (126, 20, CAST(N'2022-09-18T22:18:07.0240000' AS DateTime2), CAST(N'2022-09-19T03:06:07.0240000' AS DateTime2), 0, 24000, 62, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (127, 12, CAST(N'2022-08-24T08:58:19.7510000' AS DateTime2), CAST(N'2022-08-24T10:10:19.7510000' AS DateTime2), 0, 12000, 78, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (128, 20, CAST(N'2021-09-09T18:57:34.3930000' AS DateTime2), CAST(N'2021-09-09T21:57:34.3930000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (129, 17, CAST(N'2022-11-02T05:39:39.6050000' AS DateTime2), CAST(N'2022-11-02T07:03:39.6050000' AS DateTime2), 0, 14000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (130, 14, CAST(N'2022-10-01T17:12:05.4810000' AS DateTime2), CAST(N'2022-10-01T18:24:05.4810000' AS DateTime2), 0, 12000, 8, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (131, 18, CAST(N'2022-03-10T10:48:46.8030000' AS DateTime2), CAST(N'2022-03-10T11:24:46.8030000' AS DateTime2), 0, 6000, 92, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (132, 15, CAST(N'2022-07-02T08:32:02.9620000' AS DateTime2), CAST(N'2022-07-02T09:38:02.9620000' AS DateTime2), 0, 11000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (133, 4, CAST(N'2022-02-16T10:27:44.3220000' AS DateTime2), CAST(N'2022-02-16T12:27:44.3220000' AS DateTime2), 0, 10000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (134, 5, CAST(N'2022-04-16T06:50:24.4220000' AS DateTime2), CAST(N'2022-04-16T10:50:24.4220000' AS DateTime2), 0, 20000, 30, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (135, 7, CAST(N'2022-11-12T08:18:48.2760000' AS DateTime2), CAST(N'2022-11-12T10:48:48.2760000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (136, 11, CAST(N'2021-04-03T01:30:00.5570000' AS DateTime2), CAST(N'2021-04-03T04:42:00.5570000' AS DateTime2), 0, 16000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (137, 20, CAST(N'2021-10-13T01:12:24.5190000' AS DateTime2), CAST(N'2021-10-13T04:48:24.5190000' AS DateTime2), 0, 18000, 89, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (138, 12, CAST(N'2021-10-30T14:41:24.2690000' AS DateTime2), CAST(N'2021-10-30T15:41:24.2690000' AS DateTime2), 0, 10000, 73, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (139, 5, CAST(N'2022-11-25T12:03:43.9480000' AS DateTime2), CAST(N'2022-11-25T14:51:43.9480000' AS DateTime2), 0, 14000, 14, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (140, 3, CAST(N'2021-11-22T03:07:44.3520000' AS DateTime2), CAST(N'2021-11-22T04:43:44.3520000' AS DateTime2), 0, 8000, 93, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (141, 8, CAST(N'2021-11-20T12:30:24.9900000' AS DateTime2), CAST(N'2021-11-20T14:30:24.9900000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (142, 8, CAST(N'2021-11-21T00:59:24.7250000' AS DateTime2), CAST(N'2021-11-21T02:41:24.7250000' AS DateTime2), 0, 17000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (143, 16, CAST(N'2022-07-12T18:58:39.7380000' AS DateTime2), CAST(N'2022-07-12T21:22:39.7380000' AS DateTime2), 0, 24000, 70, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (144, 1, CAST(N'2021-04-13T10:16:26.3230000' AS DateTime2), CAST(N'2021-04-13T14:16:26.3230000' AS DateTime2), 0, 20000, 95, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (145, 1, CAST(N'2021-09-13T00:46:04.9270000' AS DateTime2), CAST(N'2021-09-13T03:46:04.9270000' AS DateTime2), 0, 15000, 77, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (146, 12, CAST(N'2022-01-07T21:00:27.4640000' AS DateTime2), CAST(N'2022-01-07T23:12:27.4640000' AS DateTime2), 0, 22000, 63, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (147, 12, CAST(N'2021-04-07T20:42:59.2190000' AS DateTime2), CAST(N'2021-04-07T22:06:59.2190000' AS DateTime2), 0, 14000, 82, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (148, 6, CAST(N'2022-01-20T00:22:24.4940000' AS DateTime2), CAST(N'2022-01-20T00:58:24.4940000' AS DateTime2), 0, 6000, 90, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (149, 18, CAST(N'2021-12-06T00:30:10.1540000' AS DateTime2), CAST(N'2021-12-06T01:36:10.1540000' AS DateTime2), 0, 11000, 79, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (150, 17, CAST(N'2022-07-14T00:50:37.8070000' AS DateTime2), CAST(N'2022-07-14T01:56:37.8070000' AS DateTime2), 0, 11000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (151, 11, CAST(N'2022-06-18T17:18:01.5890000' AS DateTime2), CAST(N'2022-06-18T18:42:01.5890000' AS DateTime2), 0, 7000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (152, 6, CAST(N'2021-06-14T23:37:10.6860000' AS DateTime2), CAST(N'2021-06-15T01:49:10.6860000' AS DateTime2), 0, 22000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (153, 12, CAST(N'2022-09-20T09:55:39.1030000' AS DateTime2), CAST(N'2022-09-20T11:49:39.1030000' AS DateTime2), 0, 19000, 69, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (154, 12, CAST(N'2023-01-04T02:34:04.3350000' AS DateTime2), CAST(N'2023-01-04T04:40:04.3350000' AS DateTime2), 0, 21000, 52, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (155, 6, CAST(N'2022-10-16T09:50:38.3850000' AS DateTime2), CAST(N'2022-10-16T11:20:38.3850000' AS DateTime2), 0, 15000, 37, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (156, 4, CAST(N'2021-06-03T18:08:16.8400000' AS DateTime2), CAST(N'2021-06-03T21:56:16.8400000' AS DateTime2), 0, 19000, 18, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (157, 3, CAST(N'2022-11-25T00:42:43.1920000' AS DateTime2), CAST(N'2022-11-25T04:30:43.1920000' AS DateTime2), 0, 19000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (158, 5, CAST(N'2022-07-23T00:07:49.1460000' AS DateTime2), CAST(N'2022-07-23T01:31:49.1460000' AS DateTime2), 0, 7000, 86, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (159, 16, CAST(N'2021-08-27T10:54:13.3180000' AS DateTime2), CAST(N'2021-08-27T12:42:13.3180000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (160, 5, CAST(N'2022-01-12T04:53:24.4020000' AS DateTime2), CAST(N'2022-01-12T06:29:24.4020000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (161, 20, CAST(N'2021-11-15T23:51:30.3900000' AS DateTime2), CAST(N'2021-11-16T04:27:30.3900000' AS DateTime2), 0, 23000, 72, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (162, 12, CAST(N'2022-07-28T11:23:27.3460000' AS DateTime2), CAST(N'2022-07-28T13:11:27.3460000' AS DateTime2), 0, 18000, 88, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (163, 5, CAST(N'2022-10-21T20:09:08.8920000' AS DateTime2), CAST(N'2022-10-22T00:33:08.8920000' AS DateTime2), 0, 22000, 13, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (164, 6, CAST(N'2021-07-15T06:27:19.4690000' AS DateTime2), CAST(N'2021-07-15T08:33:19.4690000' AS DateTime2), 0, 21000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (165, 9, CAST(N'2022-11-05T10:37:10.1320000' AS DateTime2), CAST(N'2022-11-05T14:49:10.1320000' AS DateTime2), 0, 21000, 34, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (166, 4, CAST(N'2022-03-08T15:16:08.1680000' AS DateTime2), CAST(N'2022-03-08T19:04:08.1680000' AS DateTime2), 0, 19000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (167, 10, CAST(N'2022-03-24T05:20:51.7450000' AS DateTime2), CAST(N'2022-03-24T07:56:51.7450000' AS DateTime2), 0, 13000, 79, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (168, 16, CAST(N'2023-02-18T05:34:42.6310000' AS DateTime2), CAST(N'2023-02-18T08:04:42.6310000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (169, 10, CAST(N'2022-07-10T16:06:44.8260000' AS DateTime2), CAST(N'2022-07-10T20:30:44.8260000' AS DateTime2), 0, 22000, 85, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (170, 4, CAST(N'2022-03-14T04:43:52.8700000' AS DateTime2), CAST(N'2022-03-14T06:55:52.8700000' AS DateTime2), 0, 11000, 103, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (171, 15, CAST(N'2022-10-12T13:27:50.6270000' AS DateTime2), CAST(N'2022-10-12T13:57:50.6270000' AS DateTime2), 0, 5000, 78, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (172, 4, CAST(N'2022-01-28T09:51:39.2150000' AS DateTime2), CAST(N'2022-01-28T12:51:39.2150000' AS DateTime2), 0, 15000, 37, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (173, 18, CAST(N'2022-07-06T07:00:02.6030000' AS DateTime2), CAST(N'2022-07-06T12:00:02.6030000' AS DateTime2), 0, 24000, 3, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (174, 20, CAST(N'2023-01-24T20:07:42.4650000' AS DateTime2), CAST(N'2023-01-24T21:07:42.4650000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (175, 12, CAST(N'2021-10-05T11:00:38.0440000' AS DateTime2), CAST(N'2021-10-05T12:48:38.0440000' AS DateTime2), 0, 18000, 75, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (176, 9, CAST(N'2021-07-06T11:52:51.8690000' AS DateTime2), CAST(N'2021-07-06T13:28:51.8690000' AS DateTime2), 0, 8000, 76, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (177, 15, CAST(N'2021-11-17T00:14:59.5230000' AS DateTime2), CAST(N'2021-11-17T02:32:59.5230000' AS DateTime2), 0, 23000, 97, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (178, 5, CAST(N'2022-10-27T01:45:51.7440000' AS DateTime2), CAST(N'2022-10-27T02:57:51.7440000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (179, 9, CAST(N'2023-02-04T11:58:52.5490000' AS DateTime2), CAST(N'2023-02-04T13:46:52.5490000' AS DateTime2), 0, 9000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (180, 5, CAST(N'2022-08-22T20:37:52.6520000' AS DateTime2), CAST(N'2022-08-23T00:37:52.6520000' AS DateTime2), 0, 20000, 24, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (181, 3, CAST(N'2023-02-11T20:27:52.1000000' AS DateTime2), CAST(N'2023-02-11T23:39:52.1000000' AS DateTime2), 0, 16000, 47, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (182, 14, CAST(N'2022-10-13T14:51:52.7260000' AS DateTime2), CAST(N'2022-10-13T15:39:52.7260000' AS DateTime2), 0, 8000, 25, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (183, 10, CAST(N'2022-12-04T13:09:29.6370000' AS DateTime2), CAST(N'2022-12-04T17:09:29.6370000' AS DateTime2), 0, 20000, 17, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (184, 14, CAST(N'2021-06-09T05:36:22.5200000' AS DateTime2), CAST(N'2021-06-09T07:30:22.5200000' AS DateTime2), 0, 19000, 17, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (185, 12, CAST(N'2021-07-07T08:30:16.1630000' AS DateTime2), CAST(N'2021-07-07T09:24:16.1630000' AS DateTime2), 0, 9000, 80, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (186, 11, CAST(N'2022-06-06T01:20:16.2300000' AS DateTime2), CAST(N'2022-06-06T02:32:16.2300000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (187, 4, CAST(N'2021-09-26T05:21:01.9970000' AS DateTime2), CAST(N'2021-09-26T08:45:01.9970000' AS DateTime2), 0, 17000, 69, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (188, 11, CAST(N'2023-01-17T22:52:05.1540000' AS DateTime2), CAST(N'2023-01-18T03:52:05.1540000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (189, 2, CAST(N'2021-06-12T02:47:48.4070000' AS DateTime2), CAST(N'2021-06-12T04:53:48.4070000' AS DateTime2), 0, 21000, 34, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (190, 15, CAST(N'2021-08-29T11:34:57.2860000' AS DateTime2), CAST(N'2021-08-29T13:16:57.2860000' AS DateTime2), 0, 17000, 12, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (191, 20, CAST(N'2023-02-14T15:49:53.3820000' AS DateTime2), CAST(N'2023-02-14T19:49:53.3820000' AS DateTime2), 0, 20000, 30, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (192, 16, CAST(N'2021-11-25T18:52:11.5200000' AS DateTime2), CAST(N'2021-11-25T21:16:11.5200000' AS DateTime2), 0, 24000, 54, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (193, 17, CAST(N'2022-03-16T02:49:53.5300000' AS DateTime2), CAST(N'2022-03-16T03:19:53.5300000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (194, 20, CAST(N'2021-08-24T02:05:41.2690000' AS DateTime2), CAST(N'2021-08-24T04:17:41.2690000' AS DateTime2), 0, 11000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (195, 10, CAST(N'2021-09-11T23:10:12.7960000' AS DateTime2), CAST(N'2021-09-12T00:10:12.7960000' AS DateTime2), 0, 5000, 55, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (196, 15, CAST(N'2022-01-20T21:06:26.9760000' AS DateTime2), CAST(N'2022-01-20T22:18:26.9760000' AS DateTime2), 0, 12000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (197, 12, CAST(N'2022-07-05T21:27:32.2350000' AS DateTime2), CAST(N'2022-07-05T23:21:32.2350000' AS DateTime2), 0, 19000, 61, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (198, 20, CAST(N'2022-06-30T22:20:27.5270000' AS DateTime2), CAST(N'2022-07-01T00:56:27.5270000' AS DateTime2), 0, 13000, 102, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (199, 12, CAST(N'2022-09-11T02:09:54.6230000' AS DateTime2), CAST(N'2022-09-11T03:15:54.6230000' AS DateTime2), 0, 11000, 59, NULL)
GO
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (200, 6, CAST(N'2022-07-30T08:16:03.4300000' AS DateTime2), CAST(N'2022-07-30T10:10:03.4300000' AS DateTime2), 0, 19000, 67, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (201, 6, CAST(N'2021-04-21T15:45:48.3310000' AS DateTime2), CAST(N'2021-04-21T17:21:48.3310000' AS DateTime2), 0, 16000, 82, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (202, 16, CAST(N'2021-10-07T06:04:35.8750000' AS DateTime2), CAST(N'2021-10-07T08:04:35.8750000' AS DateTime2), 0, 20000, 39, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (203, 15, CAST(N'2022-07-29T14:28:12.9870000' AS DateTime2), CAST(N'2022-07-29T16:34:12.9870000' AS DateTime2), 0, 21000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (204, 7, CAST(N'2022-01-26T21:48:33.2420000' AS DateTime2), CAST(N'2022-01-26T23:06:33.2420000' AS DateTime2), 0, 13000, 21, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (205, 2, CAST(N'2021-10-29T12:24:47.3190000' AS DateTime2), CAST(N'2021-10-29T13:06:47.3190000' AS DateTime2), 0, 7000, 61, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (206, 2, CAST(N'2022-07-27T07:15:56.8360000' AS DateTime2), CAST(N'2022-07-27T09:33:56.8360000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (207, 4, CAST(N'2022-01-27T01:19:33.9850000' AS DateTime2), CAST(N'2022-01-27T03:43:33.9850000' AS DateTime2), 0, 12000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (208, 17, CAST(N'2022-09-29T13:02:35.7850000' AS DateTime2), CAST(N'2022-09-29T13:32:35.7850000' AS DateTime2), 0, 5000, 77, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (209, 10, CAST(N'2021-06-20T23:06:03.2170000' AS DateTime2), CAST(N'2021-06-21T02:42:03.2170000' AS DateTime2), 0, 18000, 18, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (210, 5, CAST(N'2022-09-12T23:47:10.4900000' AS DateTime2), CAST(N'2022-09-13T02:35:10.4900000' AS DateTime2), 0, 14000, 23, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (211, 7, CAST(N'2022-09-09T20:35:45.4720000' AS DateTime2), CAST(N'2022-09-09T22:17:45.4720000' AS DateTime2), 0, 17000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (212, 5, CAST(N'2022-10-01T18:22:35.8910000' AS DateTime2), CAST(N'2022-10-01T21:58:35.8910000' AS DateTime2), 0, 18000, 28, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (213, 2, CAST(N'2022-10-27T02:38:30.5630000' AS DateTime2), CAST(N'2022-10-27T03:44:30.5630000' AS DateTime2), 0, 11000, 10, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (214, 6, CAST(N'2022-04-19T15:10:25.9590000' AS DateTime2), CAST(N'2022-04-19T16:22:25.9590000' AS DateTime2), 0, 12000, 11, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (215, 14, CAST(N'2021-12-06T09:30:46.7150000' AS DateTime2), CAST(N'2021-12-06T11:00:46.7150000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (216, 5, CAST(N'2023-02-17T04:05:08.7630000' AS DateTime2), CAST(N'2023-02-17T05:41:08.7630000' AS DateTime2), 0, 8000, 15, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (217, 4, CAST(N'2023-01-04T00:22:04.4480000' AS DateTime2), CAST(N'2023-01-04T03:58:04.4480000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (218, 12, CAST(N'2022-01-15T01:44:23.2690000' AS DateTime2), CAST(N'2022-01-15T02:56:23.2690000' AS DateTime2), 0, 12000, 49, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (219, 17, CAST(N'2022-08-01T05:19:25.0430000' AS DateTime2), CAST(N'2022-08-01T06:01:25.0430000' AS DateTime2), 0, 7000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (220, 20, CAST(N'2021-10-07T22:55:58.7970000' AS DateTime2), CAST(N'2021-10-08T02:55:58.7970000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (221, 4, CAST(N'2021-06-24T04:12:31.5620000' AS DateTime2), CAST(N'2021-06-24T06:12:31.5620000' AS DateTime2), 0, 10000, 100, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (222, 2, CAST(N'2021-07-23T18:02:28.7620000' AS DateTime2), CAST(N'2021-07-23T19:26:28.7620000' AS DateTime2), 0, 14000, 20, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (223, 20, CAST(N'2021-03-12T01:30:40.4680000' AS DateTime2), CAST(N'2021-03-12T03:54:40.4680000' AS DateTime2), 0, 12000, 105, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (224, 14, CAST(N'2022-09-21T12:49:06.4100000' AS DateTime2), CAST(N'2022-09-21T14:07:06.4100000' AS DateTime2), 0, 13000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (225, 13, CAST(N'2022-12-11T21:22:41.0270000' AS DateTime2), CAST(N'2022-12-12T02:22:41.0270000' AS DateTime2), 0, 25000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (226, 13, CAST(N'2022-12-30T23:27:11.2750000' AS DateTime2), CAST(N'2022-12-31T03:51:11.2750000' AS DateTime2), 0, 22000, 22, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (227, 1, CAST(N'2022-09-24T13:53:38.9020000' AS DateTime2), CAST(N'2022-09-24T16:29:38.9020000' AS DateTime2), 0, 13000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (228, 11, CAST(N'2023-01-24T09:24:16.7460000' AS DateTime2), CAST(N'2023-01-24T10:36:16.7460000' AS DateTime2), 0, 6000, 60, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (229, 16, CAST(N'2021-05-15T12:51:09.8420000' AS DateTime2), CAST(N'2021-05-15T13:57:09.8420000' AS DateTime2), 0, 11000, 75, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (230, 8, CAST(N'2021-06-18T17:39:38.9580000' AS DateTime2), CAST(N'2021-06-18T19:27:38.9580000' AS DateTime2), 0, 18000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (231, 8, CAST(N'2022-01-12T20:32:33.8800000' AS DateTime2), CAST(N'2022-01-12T21:02:33.8800000' AS DateTime2), 0, 5000, 104, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (232, 6, CAST(N'2021-09-14T23:20:09.1510000' AS DateTime2), CAST(N'2021-09-15T00:14:09.1510000' AS DateTime2), 0, 9000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (233, 20, CAST(N'2022-07-09T16:06:12.6260000' AS DateTime2), CAST(N'2022-07-09T17:06:12.6260000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (234, 3, CAST(N'2021-08-12T09:56:24.0150000' AS DateTime2), CAST(N'2021-08-12T13:20:24.0150000' AS DateTime2), 0, 17000, 98, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (235, 6, CAST(N'2021-02-24T06:01:48.5220000' AS DateTime2), CAST(N'2021-02-24T06:37:48.5220000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (236, 17, CAST(N'2022-02-01T04:26:24.0520000' AS DateTime2), CAST(N'2022-02-01T06:02:24.0520000' AS DateTime2), 0, 16000, 9, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (237, 16, CAST(N'2022-10-21T09:46:52.7900000' AS DateTime2), CAST(N'2022-10-21T11:22:52.7900000' AS DateTime2), 0, 16000, 68, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (238, 9, CAST(N'2021-05-17T19:52:14.1400000' AS DateTime2), CAST(N'2021-05-17T23:52:14.1400000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (239, 10, CAST(N'2022-05-11T01:38:40.1180000' AS DateTime2), CAST(N'2022-05-11T02:50:40.1180000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (240, 7, CAST(N'2021-04-05T01:35:56.4870000' AS DateTime2), CAST(N'2021-04-05T02:47:56.4870000' AS DateTime2), 0, 12000, 58, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (241, 4, CAST(N'2021-11-30T07:15:34.7100000' AS DateTime2), CAST(N'2021-11-30T08:51:34.7100000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (242, 2, CAST(N'2023-01-13T00:59:46.4490000' AS DateTime2), CAST(N'2023-01-13T02:11:46.4490000' AS DateTime2), 0, 12000, 80, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (243, 3, CAST(N'2022-05-14T20:21:00.8800000' AS DateTime2), CAST(N'2022-05-15T00:33:00.8800000' AS DateTime2), 0, 21000, 72, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (244, 9, CAST(N'2021-08-21T04:06:06.3780000' AS DateTime2), CAST(N'2021-08-21T07:06:06.3780000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (245, 4, CAST(N'2021-02-21T23:35:55.5900000' AS DateTime2), CAST(N'2021-02-22T02:35:55.5900000' AS DateTime2), 0, 15000, 93, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (246, 18, CAST(N'2021-04-07T01:14:37.4160000' AS DateTime2), CAST(N'2021-04-07T02:32:37.4160000' AS DateTime2), 0, 13000, 45, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (247, 2, CAST(N'2022-09-17T21:38:22.7490000' AS DateTime2), CAST(N'2022-09-18T00:02:22.7490000' AS DateTime2), 0, 24000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (248, 3, CAST(N'2022-11-01T06:47:44.3070000' AS DateTime2), CAST(N'2022-11-01T10:47:44.3070000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (249, 1, CAST(N'2022-04-22T17:07:29.4040000' AS DateTime2), CAST(N'2022-04-22T19:43:29.4040000' AS DateTime2), 0, 13000, 9, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (250, 1, CAST(N'2021-05-31T07:59:36.7110000' AS DateTime2), CAST(N'2021-05-31T09:23:36.7110000' AS DateTime2), 0, 7000, 94, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (251, 2, CAST(N'2021-08-23T03:32:54.9950000' AS DateTime2), CAST(N'2021-08-23T04:32:54.9950000' AS DateTime2), 0, 10000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (252, 18, CAST(N'2021-07-25T20:39:21.0330000' AS DateTime2), CAST(N'2021-07-25T22:03:21.0330000' AS DateTime2), 0, 14000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (253, 4, CAST(N'2022-10-12T13:14:15.5350000' AS DateTime2), CAST(N'2022-10-12T14:14:15.5350000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (254, 11, CAST(N'2021-04-27T20:53:00.0290000' AS DateTime2), CAST(N'2021-04-28T00:05:00.0290000' AS DateTime2), 0, 16000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (255, 19, CAST(N'2021-09-01T08:49:53.4550000' AS DateTime2), CAST(N'2021-09-01T09:19:53.4550000' AS DateTime2), 0, 5000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (256, 10, CAST(N'2021-09-14T18:34:44.0640000' AS DateTime2), CAST(N'2021-09-14T20:34:44.0640000' AS DateTime2), 0, 10000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (257, 4, CAST(N'2021-08-12T08:08:14.3750000' AS DateTime2), CAST(N'2021-08-12T11:08:14.3750000' AS DateTime2), 0, 15000, 48, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (258, 3, CAST(N'2022-03-23T11:54:04.2490000' AS DateTime2), CAST(N'2022-03-23T14:30:04.2490000' AS DateTime2), 0, 13000, 52, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (259, 1, CAST(N'2021-07-10T10:33:04.9500000' AS DateTime2), CAST(N'2021-07-10T12:09:04.9500000' AS DateTime2), 0, 8000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (260, 7, CAST(N'2022-09-30T00:32:03.7630000' AS DateTime2), CAST(N'2022-09-30T01:02:03.7630000' AS DateTime2), 0, 5000, 11, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (261, 10, CAST(N'2022-09-29T18:14:55.5220000' AS DateTime2), CAST(N'2022-09-29T22:50:55.5220000' AS DateTime2), 0, 23000, 73, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (262, 20, CAST(N'2021-07-10T08:36:53.6490000' AS DateTime2), CAST(N'2021-07-10T13:24:53.6490000' AS DateTime2), 0, 24000, 88, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (263, 2, CAST(N'2021-12-28T02:22:48.5900000' AS DateTime2), CAST(N'2021-12-28T04:04:48.5900000' AS DateTime2), 0, 17000, 65, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (264, 18, CAST(N'2022-11-03T10:40:25.3740000' AS DateTime2), CAST(N'2022-11-03T12:58:25.3740000' AS DateTime2), 0, 23000, 36, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (265, 17, CAST(N'2022-04-07T11:24:52.6070000' AS DateTime2), CAST(N'2022-04-07T12:42:52.6070000' AS DateTime2), 0, 13000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (266, 4, CAST(N'2022-09-17T18:29:49.5000000' AS DateTime2), CAST(N'2022-09-17T19:41:49.5000000' AS DateTime2), 0, 6000, 9, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (267, 18, CAST(N'2022-07-19T05:15:15.4100000' AS DateTime2), CAST(N'2022-07-19T06:39:15.4100000' AS DateTime2), 0, 14000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (268, 16, CAST(N'2022-11-11T01:48:14.1710000' AS DateTime2), CAST(N'2022-11-11T04:00:14.1710000' AS DateTime2), 0, 22000, 24, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (269, 10, CAST(N'2021-09-02T06:41:51.2530000' AS DateTime2), CAST(N'2021-09-02T11:05:51.2530000' AS DateTime2), 0, 22000, 64, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (270, 6, CAST(N'2021-10-06T04:43:07.4510000' AS DateTime2), CAST(N'2021-10-06T06:13:07.4510000' AS DateTime2), 0, 15000, 103, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (271, 19, CAST(N'2021-09-22T22:32:25.4570000' AS DateTime2), CAST(N'2021-09-22T23:32:25.4570000' AS DateTime2), 0, 10000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (272, 10, CAST(N'2021-07-11T17:35:33.5150000' AS DateTime2), CAST(N'2021-07-11T20:35:33.5150000' AS DateTime2), 0, 15000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (273, 11, CAST(N'2021-07-29T00:34:32.5480000' AS DateTime2), CAST(N'2021-07-29T01:46:32.5480000' AS DateTime2), 0, 6000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (274, 10, CAST(N'2021-10-22T12:32:18.8000000' AS DateTime2), CAST(N'2021-10-22T15:44:18.8000000' AS DateTime2), 0, 16000, 26, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (275, 5, CAST(N'2021-09-17T23:17:55.6580000' AS DateTime2), CAST(N'2021-09-18T02:41:55.6580000' AS DateTime2), 0, 17000, 53, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (276, 8, CAST(N'2022-11-01T17:38:13.0170000' AS DateTime2), CAST(N'2022-11-01T19:38:13.0170000' AS DateTime2), 0, 20000, 82, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (277, 17, CAST(N'2022-12-13T08:53:03.6920000' AS DateTime2), CAST(N'2022-12-13T10:17:03.6920000' AS DateTime2), 0, 14000, 29, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (278, 16, CAST(N'2021-04-23T00:09:56.5140000' AS DateTime2), CAST(N'2021-04-23T02:09:56.5140000' AS DateTime2), 0, 20000, 78, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (279, 19, CAST(N'2021-06-17T20:52:36.3030000' AS DateTime2), CAST(N'2021-06-17T22:22:36.3030000' AS DateTime2), 0, 15000, 102, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (280, 19, CAST(N'2021-08-13T12:22:59.3020000' AS DateTime2), CAST(N'2021-08-13T13:10:59.3020000' AS DateTime2), 0, 8000, 104, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (281, 7, CAST(N'2022-01-30T23:30:50.9750000' AS DateTime2), CAST(N'2022-01-31T00:30:50.9750000' AS DateTime2), 0, 10000, 50, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (282, 8, CAST(N'2022-05-19T06:53:59.1450000' AS DateTime2), CAST(N'2022-05-19T09:11:59.1450000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (283, 12, CAST(N'2021-03-13T00:23:56.9370000' AS DateTime2), CAST(N'2021-03-13T02:41:56.9370000' AS DateTime2), 0, 23000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (284, 7, CAST(N'2021-03-11T09:07:14.6580000' AS DateTime2), CAST(N'2021-03-11T10:13:14.6580000' AS DateTime2), 0, 11000, 34, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (285, 20, CAST(N'2021-02-16T02:55:18.3480000' AS DateTime2), CAST(N'2021-02-16T05:07:18.3480000' AS DateTime2), 0, 11000, 79, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (286, 14, CAST(N'2023-02-27T02:59:56.5020000' AS DateTime2), CAST(N'2023-02-27T04:11:56.5020000' AS DateTime2), 0, 12000, 17, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (287, 13, CAST(N'2022-04-22T19:53:15.7270000' AS DateTime2), CAST(N'2022-04-22T23:41:15.7270000' AS DateTime2), 0, 19000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (288, 7, CAST(N'2022-06-25T14:55:46.3440000' AS DateTime2), CAST(N'2022-06-25T17:25:46.3440000' AS DateTime2), 0, 25000, 86, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (289, 13, CAST(N'2022-12-23T08:15:04.6930000' AS DateTime2), CAST(N'2022-12-23T12:15:04.6930000' AS DateTime2), 0, 20000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (290, 20, CAST(N'2021-06-02T19:40:56.2810000' AS DateTime2), CAST(N'2021-06-02T21:16:56.2810000' AS DateTime2), 0, 8000, 94, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (291, 8, CAST(N'2022-04-13T08:13:51.8280000' AS DateTime2), CAST(N'2022-04-13T10:37:51.8280000' AS DateTime2), 0, 24000, 96, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (292, 6, CAST(N'2022-01-07T22:31:26.0190000' AS DateTime2), CAST(N'2022-01-07T23:43:26.0190000' AS DateTime2), 0, 12000, 34, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (293, 11, CAST(N'2021-05-28T06:19:53.1650000' AS DateTime2), CAST(N'2021-05-28T10:43:53.1650000' AS DateTime2), 0, 22000, 86, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (294, 20, CAST(N'2021-05-07T19:36:50.8430000' AS DateTime2), CAST(N'2021-05-07T21:12:50.8430000' AS DateTime2), 0, 8000, 98, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (295, 20, CAST(N'2022-03-25T23:13:48.8120000' AS DateTime2), CAST(N'2022-03-26T01:37:48.8120000' AS DateTime2), 0, 12000, 26, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (296, 9, CAST(N'2022-11-18T16:52:28.7070000' AS DateTime2), CAST(N'2022-11-18T19:16:28.7070000' AS DateTime2), 0, 12000, NULL, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (297, 16, CAST(N'2021-11-30T03:56:49.3270000' AS DateTime2), CAST(N'2021-11-30T05:20:49.3270000' AS DateTime2), 0, 14000, 31, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (298, 19, CAST(N'2022-03-27T23:47:12.3540000' AS DateTime2), CAST(N'2022-03-28T00:53:12.3540000' AS DateTime2), 0, 11000, 24, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (299, 14, CAST(N'2022-04-29T07:01:00.0140000' AS DateTime2), CAST(N'2022-04-29T09:13:00.0140000' AS DateTime2), 0, 22000, 83, NULL)
GO
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (300, 3, CAST(N'2022-08-05T14:47:44.9610000' AS DateTime2), CAST(N'2022-08-05T17:47:44.9610000' AS DateTime2), 0, 15000, 34, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (313, 2, CAST(N'2023-04-20T16:03:53.7660000' AS DateTime2), CAST(N'2023-04-20T16:04:52.1420000' AS DateTime2), 0, 833, 12, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (314, NULL, CAST(N'2022-03-03T10:10:25.7150000' AS DateTime2), CAST(N'2022-03-03T14:10:25.7150000' AS DateTime2), 1, 63840, 3, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (315, NULL, CAST(N'2022-05-01T18:24:53.7640000' AS DateTime2), CAST(N'2022-05-02T03:24:53.7640000' AS DateTime2), 1, 179820, 4, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (316, NULL, CAST(N'2022-01-13T15:53:07.3790000' AS DateTime2), CAST(N'2022-01-13T18:53:07.3790000' AS DateTime2), 1, 47880, 3, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (317, NULL, CAST(N'2022-01-07T12:49:20.3820000' AS DateTime2), CAST(N'2022-01-07T20:49:20.3820000' AS DateTime2), 1, 127680, 3, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (318, NULL, CAST(N'2021-11-13T01:49:20.3680000' AS DateTime2), CAST(N'2021-11-13T08:49:20.3680000' AS DateTime2), 1, 139860, 4, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (319, NULL, CAST(N'2023-01-31T22:21:02.5980000' AS DateTime2), CAST(N'2023-02-01T07:21:02.5980000' AS DateTime2), 1, 143640, 3, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (320, NULL, CAST(N'2022-07-30T11:41:32.3530000' AS DateTime2), CAST(N'2022-07-30T19:41:32.3530000' AS DateTime2), 1, 175680, 5, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (321, NULL, CAST(N'2021-08-06T17:46:24.2500000' AS DateTime2), CAST(N'2021-08-07T02:46:24.2500000' AS DateTime2), 1, 89640, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (322, NULL, CAST(N'2022-07-26T12:35:06.4490000' AS DateTime2), CAST(N'2022-07-26T15:35:06.4490000' AS DateTime2), 1, 29880, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (323, NULL, CAST(N'2022-08-17T13:44:17.4960000' AS DateTime2), CAST(N'2022-08-17T15:44:17.4960000' AS DateTime2), 1, 43920, 5, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (324, NULL, CAST(N'2022-01-16T14:45:31.5120000' AS DateTime2), CAST(N'2022-01-16T23:45:31.5120000' AS DateTime2), 1, 89640, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (325, NULL, CAST(N'2022-12-09T10:22:13.6510000' AS DateTime2), CAST(N'2022-12-09T17:22:13.6510000' AS DateTime2), 1, 69720, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (326, NULL, CAST(N'2023-04-10T15:12:06.8260000' AS DateTime2), CAST(N'2023-04-10T19:12:06.8260000' AS DateTime2), 1, 87840, 5, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (327, NULL, CAST(N'2021-11-16T09:20:12.6730000' AS DateTime2), CAST(N'2021-11-16T17:20:12.6730000' AS DateTime2), 1, 79680, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (328, NULL, CAST(N'2022-09-07T06:46:53.6120000' AS DateTime2), CAST(N'2022-09-07T09:46:53.6120000' AS DateTime2), 1, 29880, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (329, NULL, CAST(N'2022-09-18T04:21:46.4800000' AS DateTime2), CAST(N'2022-09-18T11:21:46.4800000' AS DateTime2), 1, 153720, 5, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (330, NULL, CAST(N'2022-07-28T10:22:58.6810000' AS DateTime2), CAST(N'2022-07-28T13:22:58.6810000' AS DateTime2), 1, 29880, 6, NULL)
INSERT [dbo].[ComputerUsage] ([id], [computerID], [createdAt], [endAt], [isEmployeeUsing], [totalMoney], [usedByAccountId], [usedBy]) VALUES (331, NULL, CAST(N'2021-12-04T06:27:41.0100000' AS DateTime2), CAST(N'2021-12-04T13:27:41.0100000' AS DateTime2), 1, 69720, 6, NULL)
SET IDENTITY_INSERT [dbo].[ComputerUsage] OFF
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (1, 1, CAST(N'2023-03-10T15:25:30.3550000' AS DateTime2), NULL, N'admin', N'admin', 0, N'0376507260     ', N'Tran Binh Trong ')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (2, 2, CAST(N'2023-03-10T15:25:30.3610000' AS DateTime2), NULL, N'Nguyễn Van A', N'Chủ cửa hàng', 0, N'0376507260     ', N'An Duong Vuong')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (3, 3, CAST(N'2023-03-10T15:25:30.3650000' AS DateTime2), NULL, N'Nhân viên 1', N'Nhân viên 1', 16000, N'000000001      ', N'An Duong Vuong')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (4, 4, CAST(N'2023-03-10T15:25:30.3680000' AS DateTime2), NULL, N'Nhân viên 2', N'Nhân viên 2', 20000, N'0000002121     ', N'An Duong Vuong')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (5, 5, CAST(N'2023-03-10T15:25:30.3720000' AS DateTime2), NULL, N'Nhân viên 3', N'Nhân viên 3', 22000, N'0010101024     ', N'An Duong Vuong')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (6, 6, CAST(N'2023-03-10T15:25:30.3740000' AS DateTime2), NULL, N'Nhân viên 4', N'Nhân viên 4', 10000, N'00602588974    ', N'An Duong Vuong')
INSERT [dbo].[Employee] ([id], [accountID], [createdAt], [deletedAt], [name], [otherInformation], [salaryPerHour], [phoneNumber], [address]) VALUES (7, 7, CAST(N'2023-03-10T15:25:30.3770000' AS DateTime2), NULL, N'Nhân viên 5', N'Nhân viên 5', 15000, N'06241520522    ', N'An Duong Vuong')
SET IDENTITY_INSERT [dbo].[Employee] OFF
GO
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (1, NULL, CAST(N'2021-03-18T04:34:21.0570000' AS DateTime2), 4, NULL, NULL, 0, NULL, 3, 10971090, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (2, NULL, CAST(N'2022-08-06T11:57:28.2420000' AS DateTime2), 3, NULL, NULL, 0, NULL, 3, 3784960, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (3, NULL, CAST(N'2022-09-12T00:06:24.0730000' AS DateTime2), 4, NULL, NULL, 0, NULL, 3, 10718270, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (4, NULL, CAST(N'2021-03-09T16:37:19.8000000' AS DateTime2), 2, NULL, NULL, 0, NULL, 3, 2316500, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (5, NULL, CAST(N'2022-05-02T07:10:17.2610000' AS DateTime2), 2, NULL, NULL, 0, NULL, 3, 18098400, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (6, NULL, CAST(N'2021-03-04T23:32:28.8910000' AS DateTime2), 6, NULL, NULL, 0, NULL, 3, 2959875, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (7, NULL, CAST(N'2021-02-12T05:45:37.9410000' AS DateTime2), 7, NULL, NULL, 0, NULL, 3, 1538295, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (8, NULL, CAST(N'2021-04-20T12:32:21.9320000' AS DateTime2), 7, NULL, NULL, 0, NULL, 3, 2985640, 0)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (9, 10, CAST(N'2021-11-19T22:21:26.6000000' AS DateTime2), 4, 11, CAST(N'2023-04-20T16:55:46.9700000' AS DateTime2), 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (10, 18, CAST(N'2021-05-06T19:24:02.4360000' AS DateTime2), 5, 13, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (11, 15, CAST(N'2022-12-24T20:44:39.9790000' AS DateTime2), 2, 11, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (12, 8, CAST(N'2022-08-05T23:20:16.4610000' AS DateTime2), 4, NULL, CAST(N'2023-04-20T16:55:35.6333333' AS DateTime2), 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (13, 11, CAST(N'2023-01-23T22:10:49.9760000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 40000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (14, 12, CAST(N'2021-07-24T11:13:26.2920000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (15, 18, CAST(N'2022-05-13T05:01:06.1230000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 10000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (16, 6, CAST(N'2023-01-24T01:02:46.8070000' AS DateTime2), 2, 11, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (17, 7, CAST(N'2022-07-30T21:11:53.9900000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 10000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (18, 11, CAST(N'2021-11-03T20:52:17.9730000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (19, 8, CAST(N'2021-10-22T18:46:14.7030000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (20, 15, CAST(N'2022-02-07T13:43:21.3020000' AS DateTime2), 6, 13, NULL, 1, NULL, 3, 45000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (21, 7, CAST(N'2021-08-20T07:42:50.8280000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (22, 10, CAST(N'2022-05-17T04:50:58.2240000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (23, 10, CAST(N'2023-02-04T08:16:18.7270000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (24, 20, CAST(N'2022-02-16T07:35:04.6430000' AS DateTime2), 5, NULL, NULL, 1, NULL, 3, 100000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (25, 20, CAST(N'2021-08-11T00:40:33.7560000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (26, 14, CAST(N'2022-10-01T18:01:34.4300000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 15000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (27, 15, CAST(N'2022-07-02T09:15:19.4860000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 15000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (28, 16, CAST(N'2022-07-12T21:21:38.3900000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 100000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (29, 6, CAST(N'2022-01-20T00:37:24.2760000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (30, 18, CAST(N'2021-12-06T01:03:53.7700000' AS DateTime2), 3, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (31, 9, CAST(N'2022-11-05T13:51:07.3070000' AS DateTime2), 5, NULL, NULL, 1, NULL, 3, 120000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (32, 10, CAST(N'2022-07-10T18:14:45.8620000' AS DateTime2), 2, 14, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (33, 4, CAST(N'2022-03-14T06:27:47.8780000' AS DateTime2), 5, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (34, 5, CAST(N'2022-08-22T22:32:47.7860000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (35, 3, CAST(N'2023-02-11T20:34:47.8960000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (36, 12, CAST(N'2021-07-07T09:12:04.1740000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 10000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (37, 20, CAST(N'2022-06-30T23:15:38.6820000' AS DateTime2), 5, 12, NULL, 1, NULL, 3, 10000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (38, 16, CAST(N'2021-10-07T06:21:34.2450000' AS DateTime2), 3, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (39, 5, CAST(N'2022-09-13T00:58:36.6100000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (40, 5, CAST(N'2023-02-17T05:33:35.3800000' AS DateTime2), 6, 14, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (41, 20, CAST(N'2021-03-12T03:07:13.2340000' AS DateTime2), 5, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (42, 3, CAST(N'2021-08-12T12:30:41.7130000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 15000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (43, 17, CAST(N'2022-02-01T05:28:12.0570000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 65000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (44, 4, CAST(N'2021-02-22T02:24:55.1740000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 20000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (45, 10, CAST(N'2022-09-29T18:20:36.4450000' AS DateTime2), 6, NULL, NULL, 1, NULL, 3, 15000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (46, 20, CAST(N'2021-07-10T13:00:40.1950000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (47, 10, CAST(N'2021-09-02T08:22:11.3410000' AS DateTime2), 5, 19, NULL, 1, NULL, 3, 130000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (48, 5, CAST(N'2021-09-18T00:27:03.8130000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 50000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (49, 17, CAST(N'2022-12-13T09:02:27.1220000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (50, 16, CAST(N'2021-04-23T01:00:05.8530000' AS DateTime2), 3, NULL, NULL, 1, NULL, 3, 100000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (51, 19, CAST(N'2021-06-17T21:24:05.0610000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 135000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (52, 7, CAST(N'2022-01-31T00:30:28.9230000' AS DateTime2), 5, 40, NULL, 1, NULL, 3, 145000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (53, 20, CAST(N'2021-02-16T03:51:12.2000000' AS DateTime2), 4, NULL, NULL, 1, NULL, 3, 45000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (54, 11, CAST(N'2021-05-28T10:16:36.8540000' AS DateTime2), 2, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (55, 20, CAST(N'2022-03-26T01:02:36.1130000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 30000, 1)
INSERT [dbo].[Invoice] ([id], [computerId], [createdAt], [createdBy], [createdToAccountId], [deletedAt], [isPaid], [note], [status], [total], [type]) VALUES (56, 14, CAST(N'2022-04-29T08:52:00.2830000' AS DateTime2), 7, NULL, NULL, 1, NULL, 3, 100000, 1)
SET IDENTITY_INSERT [dbo].[Invoice] OFF
GO
SET IDENTITY_INSERT [dbo].[InvoiceDetail] ON 

INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (1, 1, 466600000, 15, 21)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (2, 1, 61710000, 9, 19)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (3, 2, 67050000, 13, 22)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (4, 2, 86625000, 20, 14)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (5, 2, 87150000, 6, 6)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (6, 2, 44170000, 12, 13)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (7, 3, 199750000, 18, 20)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (8, 3, 631200000, 16, 9)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (9, 3, 80190000, 10, 13)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (10, 4, 231650000, 18, 10)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (11, 5, 714400000, 16, 24)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (12, 5, 95280000, 14, 10)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (13, 6, 53775000, 13, 5)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (14, 6, 87480000, 9, 9)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (15, 6, 118980000, 14, 16)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (16, 7, 47145000, 13, 11)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (17, 7, 84975000, 10, 12)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (18, 8, 83730000, 9, 13)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (19, 8, 72260000, 5, 20)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (20, 8, 45195000, 6, 10)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (21, 9, 30000, 2, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (22, 10, 30000, 1, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (23, 11, 30000, 3, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (24, 12, 20000, 21, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (25, 13, 30000, 1, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (26, 13, 10000, 12, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (27, 14, 30000, 8, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (28, 15, 10000, 4, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (29, 16, 30000, 3, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (30, 17, 10000, 4, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (31, 18, 20000, 5, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (32, 19, 20000, 22, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (33, 20, 30000, 1, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (34, 20, 15000, 13, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (35, 21, 30000, 19, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (36, 22, 30000, 8, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (37, 23, 20000, 21, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (38, 24, 100000, 17, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (39, 25, 30000, 1, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (40, 26, 15000, 6, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (41, 27, 15000, 20, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (42, 28, 100000, 15, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (43, 29, 30000, 7, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (44, 30, 30000, 7, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (45, 31, 20000, 22, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (46, 31, 100000, 17, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (47, 32, 30000, 1, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (48, 33, 20000, 21, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (49, 34, 30000, 8, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (50, 35, 20000, 22, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (51, 36, 10000, 4, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (52, 37, 10000, 4, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (53, 38, 15000, 20, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (54, 38, 15000, 10, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (55, 39, 30000, 8, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (56, 40, 20000, 22, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (57, 41, 20000, 5, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (58, 42, 15000, 6, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (59, 43, 15000, 6, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (60, 43, 50000, 18, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (61, 44, 20000, 22, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (62, 45, 15000, 20, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (63, 46, 30000, 8, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (64, 47, 30000, 3, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (65, 47, 100000, 17, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (66, 48, 50000, 18, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (67, 49, 30000, 7, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (68, 50, 100000, 17, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (69, 51, 20000, 5, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (70, 51, 15000, 10, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (71, 51, 100000, 15, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (72, 52, 30000, 2, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (73, 52, 15000, 13, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (74, 52, 100000, 17, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (75, 53, 30000, 7, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (76, 53, 15000, 11, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (77, 54, 30000, 3, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (78, 55, 30000, 2, 1)
INSERT [dbo].[InvoiceDetail] ([id], [invoiceId], [price], [productId], [quantity]) VALUES (79, 56, 100000, 17, 1)
SET IDENTITY_INSERT [dbo].[InvoiceDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (1, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Com chiên bò', N'/images/Cơm_chiên_bò.jpg', N'Cơm chiên bò', 30000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (2, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Com chiên trứng', N'/images/Cơm_chiên_trứng.jpg', N'Cơm chiên trứng', 30000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (3, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Nui xào bò', N'/images/Nui_xào_bò.jpg', N'Nui xào bò', 30000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (4, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Mì tôm', N'/images/Mì_tôm.jpg', N'Mì tôm', 10000, 20, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (5, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Mì tôm trứng', N'/images/Mì_tôm_trứng.jpg', N'Mì tôm trứng', 20000, 40, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (6, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Cá viên chiên', N'/images/Cá_viên_chiên.jpg', N'Cá viên chiên', 15000, 36, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (7, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Mì xào bò', N'/images/Mì_xào_bò.jpg', N'Mì xào bò', 30000, -2, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (8, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Com rang thập cẩm', N'/images/Cơm_rang_thập_cẩm.jpg', N'Com rang thập cẩm', 30000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (9, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Coca', N'/images/Coca.jpg', N'Coca', 15000, 60, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (10, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Pepsi', N'/images/Pepsi.jpg', N'Pepsi', 15000, 45, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (11, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'7up', N'/images/7up.jpg', N'7up', 15000, 20, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (12, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Nước suối', N'/images/Nước_suối.jpg', N'Nước suối', 10000, 33, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (13, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Sting', N'/images/Sting.jpg', N'Sting', 15000, 58, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (14, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Redbull', N'/images/Redbull.jpg', N'Redbull', 20000, 46, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (15, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Thẻ garena 100k', N'/images/Thẻ_garena_100k.jpg', N'Thẻ garena 100k', 100000, 41, 2)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (16, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Thẻ garena 200k', N'/images/Thẻ_garena_200k.jpg', N'Thẻ garena 200k', 200000, 53, 2)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (17, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Thẻ viettel 100k', N'/images/Thẻ_viettel_100k.jpg', N'Thẻ viettel 100k', 100000, 20, 2)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (18, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Thẻ mobi 50k', N'/images/Thẻ_mobi_50k.jpg', N'Thẻ mobi 50k', 50000, 50, 2)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (19, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Phở', N'/images/Phở.jpg', N'Phở', 30000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (20, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Cà phê', N'/images/Cà_phê.jpg', N'Cà phê', 15000, 14, 1)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (21, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Bánh mì trứng', N'/images/Bánh_mì_trứng.jpg', N'Bánh mì trứng', 20000, -1, 0)
INSERT [dbo].[Product] ([id], [createdAt], [deletedAt], [description], [image], [name], [price], [stock], [type]) VALUES (22, CAST(N'2023-03-01T01:16:46.4630000' AS DateTime2), NULL, N'Bánh mì thịt', N'/images/Bánh_mì_thịt.jpg', N'Bánh mì thịt', 20000, -1, 0)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_de34gsw4qt8auhffbna969ahp]    Script Date: 4/25/2023 8:29:09 AM ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UK_de34gsw4qt8auhffbna969ahp] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Invoice] ADD  CONSTRAINT [DF_Invoice_total]  DEFAULT ((0)) FOR [total]
GO
ALTER TABLE [dbo].[ComputerUsage]  WITH CHECK ADD  CONSTRAINT [FK1ukgw2h7yqj0uqhs4kln7b0tv] FOREIGN KEY([computerID])
REFERENCES [dbo].[Computer] ([id])
GO
ALTER TABLE [dbo].[ComputerUsage] CHECK CONSTRAINT [FK1ukgw2h7yqj0uqhs4kln7b0tv]
GO
ALTER TABLE [dbo].[ComputerUsage]  WITH CHECK ADD  CONSTRAINT [FKru3cjhi3eagngqfgdf3ap0w0f] FOREIGN KEY([usedBy])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[ComputerUsage] CHECK CONSTRAINT [FKru3cjhi3eagngqfgdf3ap0w0f]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK94h4kungplj8clo62i971c85o] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK94h4kungplj8clo62i971c85o]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK6hqjk9ej9r883fy7sgep9efn7] FOREIGN KEY([createdBy])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK6hqjk9ej9r883fy7sgep9efn7]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK937sc7o18kooa1fhxm57wld1w] FOREIGN KEY([createdToAccountId])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK937sc7o18kooa1fhxm57wld1w]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FKmhlwjt2ll4e2gnxrx2i3yfljd] FOREIGN KEY([computerId])
REFERENCES [dbo].[Computer] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FKmhlwjt2ll4e2gnxrx2i3yfljd]
GO
ALTER TABLE [dbo].[InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FK2pri5nh9cre1wwfky1gd4egw6] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[InvoiceDetail] CHECK CONSTRAINT [FK2pri5nh9cre1wwfky1gd4egw6]
GO
ALTER TABLE [dbo].[InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FKb5m8jjhsq5jxvhdbwty3d05sq] FOREIGN KEY([invoiceId])
REFERENCES [dbo].[Invoice] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[InvoiceDetail] CHECK CONSTRAINT [FKb5m8jjhsq5jxvhdbwty3d05sq]
GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_Session] FOREIGN KEY([sessionId])
REFERENCES [dbo].[Session] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_Session]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FKa5yuno1td1sbamjqf31ccqbde] FOREIGN KEY([usingBy])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FKa5yuno1td1sbamjqf31ccqbde]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FKgy250fk87rpr8iwyiwbq3b5oi] FOREIGN KEY([computerID])
REFERENCES [dbo].[Computer] ([id])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FKgy250fk87rpr8iwyiwbq3b5oi]
GO
USE [master]
GO
ALTER DATABASE [NetCF] SET  READ_WRITE 
GO
