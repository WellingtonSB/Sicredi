CREATE TABLE [Pauta] (
	Id float NOT NULL,
	Titulo string(max=40) NOT NULL,
	Texto string(max=240) NOT NULL UNIQUE,
	InicioVotacao datetime UNIQUE,
	fimVotacao datetime UNIQUE,
	pautaAtiva boolean NOT NULL,
	totalVotos integer NOT NULL,
	votosContra integer NOT NULL,
	votosFavor integer NOT NULL,
	aprovada boolean(false) NOT NULL,
  CONSTRAINT [PK_PAUTA] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
CREATE TABLE [Associado] (
	Id integer NOT NULL,
	Nome string(max=30) NOT NULL UNIQUE,
	CPF float(11) NOT NULL UNIQUE,
	jaVotou boolean(false) NOT NULL,
	voto boolean NOT NULL,
  CONSTRAINT [PK_ASSOCIADO] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
ALTER TABLE [Pauta] WITH CHECK ADD CONSTRAINT [Pauta_fk0] FOREIGN KEY ([Id]) REFERENCES [Associado]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Pauta] CHECK CONSTRAINT [Pauta_fk0]
GO

ALTER TABLE [Associado] WITH CHECK ADD CONSTRAINT [Associado_fk0] FOREIGN KEY ([Id]) REFERENCES [Pauta]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Associado] CHECK CONSTRAINT [Associado_fk0]
GO

