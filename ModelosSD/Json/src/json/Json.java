/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

//BIBLIOTECA PARA JSON -> org.json
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author diego
 */
public class Json {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {
        
            //CONVERTER JSON PARA OBJETO
                        
            //String no formato JSON recebida pelo cliente/servidor
            String json = "{protocolo:1,conteudo:{nome:\"João\"}}";
            
            System.out.println("JSON RECEBIDO: " + json);
        
            //Converte JSON para objeto java com propriedades mapeadas
            JSONObject request = new JSONObject(json);
            
            //Obtem propriedade do tipo integer mapeada no objeto de nível 1
            System.out.println("Protocolo: " + request.getInt("protocolo"));
 
            //Obtem objeto mapeada do JSON do segundo nível (conteudo)
            JSONObject conteudo = request.getJSONObject("conteudo");
            
            //Obtem propriedade do tipo string mapeada no objeto de nível 2
            System.out.println("Conteudo/Nome: " + conteudo.getString("nome") + "\n");
            
            //CONVERTER OBJETO PARA JSON
            
            //Cria um objeto do tipo JSONObject
            JSONObject response = new JSONObject();
            
            //Mapea as propriedades do nível 1 (protocolo, erro e mensagem)
            response.put("protocolo", 1);
            response.put("erro", false);
            response.put("mensagem", "Logado com sucesso!");
            
            //Cria um objeto do tipo JSONArray (cria array em JSON)            
            JSONArray clients = new JSONArray();
            
            //Cria objeto do tipo JSONObject para mapear as propriedades dos clientes que serão retornados
            JSONObject client1 = new JSONObject();
            
            //Mapea as propriedades do cliente (ip, porta e nome)
            client1.put("ip", "192.168.0.2");
            client1.put("porta", 25000);
            client1.put("nome", "Cliente 1");
            
            //Adiciona objeto do cliente ao objeto JSONArray
            clients.put(client1);
            
            //Repete os passos acima
            JSONObject client2 = new JSONObject();
            client2.put("ip", "192.168.0.3");
            client2.put("porta", 25001);
            client2.put("nome", "Cliente 2");
            clients.put(client2);
            
            //Repete os passos acima
            JSONObject client3 = new JSONObject();
            client3.put("ip", "192.168.0.4");
            client3.put("porta", 25002);
            client3.put("nome", "Cliente 3");
            clients.put(client3);
            
            //Mapea a propriedade retorno no nível 1 ao objeto JSONArray (clientes), conteúdo de nível 2
            response.put("retorno", clients);
            
            //Converte objeto JSONObject em JSON (string)
            System.out.println("JSON ENVIADO: " + response.toString());
            
        }catch(JSONException e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}
