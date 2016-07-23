package json;

import java.io.Serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**@author gabriel e pedro victor*/
/**
 * 
 * @author gabriel
 *
 * @param <T>
 */
public class JsonParser<T extends Serializable> {
	
	private Class<T> aClass;
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected String caminho;
	
	protected String simpleType;
	
	public JsonParser( Class<T> aClass, String caminho){
		this.aClass = aClass;
		this.caminho = caminho;
		
	}
	/**
	 * Grava qualquer ojeto em formator Json
	 * @param object
	 */
	public void gravaJson( Object object ) {
		try {
			System.out.println("Entrei no grava JSON");
			mapper.writeValue(new File(caminho), object);
			System.out.println("Entrei no grava JSON");
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Classe le quaquer objeto e o converte para um objeto do mesmo tipo que o passado no seu
	 * construtor
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public Object leJson(){
	   try{
			FileInputStream file  = new FileInputStream(caminho);
			JsonNode rootNode = mapper.readTree(file);
			Object object = new ObjectMapper().treeToValue(rootNode, aClass);
			return object;
			
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   return null;
	}
}

