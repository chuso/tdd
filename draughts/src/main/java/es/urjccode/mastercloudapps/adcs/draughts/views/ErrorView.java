package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class ErrorView {
    public static final String[] MESSAGES = { 
            "No te entiendo: <d><d>{,<d><d>}[0-2]",
            "No hay ficha que mover",
            "No es una de tus fichas",
            "No vas en diagonal",
            "No respetas la distancia",
            "No está vacío el destino",
            "No avanzas ",
            "No comes contrarias",
			"No se puede comer tantas en un salto", 
        };

	protected Error error;

	public ErrorView(Error error) {
		this.error = error;
	}
	
	public String getMessage() {
		return ErrorView.MESSAGES[this.error.ordinal()];
	}
	
}
