package br.com.virtz.condominio.constantes;

public enum EnumParametroSistema {
	AVISAR_POR_EMAIL_QUANDO_AGENDAR_AREA_COMUM(1l),
	QUANTIDADE_DIAS_MAXIMO_PARA_AGENDAR_AREA_COMUM(2l);
	
	private Long id;
	
	private EnumParametroSistema(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
