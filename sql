SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TB_USER](
	[cpf] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[TB_USER] ADD PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TB_ADDRESS](
	[id_address] [int] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[neighborhood] [nvarchar](255) NULL,
	[zipcode] [nvarchar](255) NULL,
	[number] [numeric](9, 2) NULL,
	[comp] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](2) NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TB_ADDRESS] ADD PRIMARY KEY CLUSTERED 
(
	[id_address] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TB_PROPERTY](
	[id_property] [int] IDENTITY(1,1) NOT NULL,
	[id_address] [int] NOT NULL,
	[price_rental] [decimal](18, 0) NULL,
	[number_rooms] [int] NULL,
	[number_bathroom] [int] NULL,
	[area] [decimal](18, 0) NULL,
	[parking] [int] NULL,
	[description] [nvarchar](255) NULL,
	[type_property] [int] NULL,
	[cpf_tenant] [nvarchar](255) NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TB_PROPERTY] ADD PRIMARY KEY CLUSTERED 
(
	[id_property] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TB_PROPERTY]  WITH CHECK ADD  CONSTRAINT [cpf_tenant] FOREIGN KEY([cpf_tenant])
REFERENCES [dbo].[TB_USER] ([cpf])
GO
ALTER TABLE [dbo].[TB_PROPERTY] CHECK CONSTRAINT [cpf_tenant]
GO
ALTER TABLE [dbo].[TB_PROPERTY]  WITH CHECK ADD  CONSTRAINT [id_address] FOREIGN KEY([id_address])
REFERENCES [dbo].[TB_ADDRESS] ([id_address])
GO
ALTER TABLE [dbo].[TB_PROPERTY] CHECK CONSTRAINT [id_address]
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TB_HOUSE](
	[id_property] [int] NOT NULL,
	[number_floor] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TB_HOUSE]  WITH CHECK ADD FOREIGN KEY([id_property])
REFERENCES [dbo].[TB_PROPERTY] ([id_property])
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TB_APARTMENT](
	[id_property] [int] NOT NULL,
	[condominium_fee] [decimal](18, 0) NULL,
	[duplex] [bit] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TB_APARTMENT]  WITH CHECK ADD FOREIGN KEY([id_property])
REFERENCES [dbo].[TB_PROPERTY] ([id_property])
GO


