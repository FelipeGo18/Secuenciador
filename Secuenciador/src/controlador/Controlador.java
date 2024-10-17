package controlador;

import vista.PanelSeleccionar;
import modelo.ReadText;

/**
 *
 * @author lokci
 */
public class Controlador {

    private ReadText rdTxt;
    private PanelSeleccionar pnlSeleccionar;
    
    public void conectar(PanelSeleccionar pnlSeleccionar, ReadText rdTxt){
        this.rdTxt = rdTxt;
        this.pnlSeleccionar = pnlSeleccionar;
        
    }
}
