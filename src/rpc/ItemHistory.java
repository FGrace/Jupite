package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;

/**
 * Servlet implementation class ItemHistory
 */
@WebServlet("/history")
public class ItemHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		String userId = request.getParameter("user_id");
		JSONArray array = new JSONArray();
		
		DBConnection conn = DBConnectionFactory.getConnection();
		Set<Item> items = conn.getFavoriteItems(userId);
	
		//convert Item from database to JSON
		for (Item item : items) {
			JSONObject obj = item.toJSONObject();
			
			try {
				obj.append("favorite", true);
			} catch(JSONException e) {
				e.printStackTrace();
			}
			
			array.put(obj);
			
		}
		
		RpcHelper.writeJsonArray(response, array);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//correspond to set favorite
	//{
    /*'user_id':'1111',
    'favorite' : [
        'item_id1',
        'item_id2'
    ]
}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//convert http request into JSONObject
			JSONObject input = RpcHelper.readJsonObject(request);
			//get userId
			String userId = input.getString("user_id");
			
			//get favorite list
			JSONArray array = input.getJSONArray("favorite");
			List<String> itemIds = new ArrayList<>();
			for (int i = 0; i < array.length();i++) {
				itemIds.add(array.get(i).toString());
			}
			
			//build dbconnection and save into history table via setFavoriteItems()
			DBConnection conn = DBConnectionFactory.getConnection();
			conn.setFavoriteItems(userId, itemIds);
			conn.close();
			
			//return a message to client
			RpcHelper.writeJasonObject(response, new JSONObject().put("result", "SUCCESS"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	//correspond to unset favorite
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject input = RpcHelper.readJsonObject(request);
			String userId = input.getString("user_id");
			
			JSONArray array = input.getJSONArray("favorite");
			List<String> itemIds = new ArrayList<>();
			for (int i = 0; i < array.length();i++) {
				itemIds.add(array.get(i).toString());
			}
			
			DBConnection conn = DBConnectionFactory.getConnection();
			conn.unsetFavoriteItems(userId, itemIds);
			conn.close();
			
			//return a message to client
			RpcHelper.writeJasonObject(response, new JSONObject().put("result", "SUCCESS"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
