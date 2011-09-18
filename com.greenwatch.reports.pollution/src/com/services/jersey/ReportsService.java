package com.services.jersey;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.reports.dao.Dao;
import com.reports.model.ItemMarshaller;
import com.reports.model.Report;




@Path("/reports")
public class ReportsService {
	
	@POST
	@Path("/text")
	@Produces({"application/xml","application/json"})
	@Consumes(MediaType.APPLICATION_XML)
	public void postReport(ItemMarshaller t){	
		System.out.println("Creating new report ");
		
		
		String summary = t.getSummary();
		String longDescription = t.getDescription();
		String url = t.getUrl();

		Dao.INSTANCE.add("niro", summary, longDescription, url);

	}
	
	
	@POST
	@Path("/image")
    @Consumes( {MediaType.TEXT_PLAIN, "image/jpeg", "image/png", "image/gif", "application/zip"} )
	@Produces( {MediaType.TEXT_PLAIN} )
	public void postReportImage(@Context HttpServletRequest req, @Context HttpServletResponse res){
					BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		            String uploadURL = blobstoreService.createUploadUrl("/upload");

		             
		             if(uploadURL.indexOf("http") == -1)
		             {
		                 uploadURL = "http://localhost:8888" + uploadURL;
		             }
		             URL url = null;
					try {
						url = new URL(uploadURL);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try{
		             
		             String boundary = MultiPartFormOutputStream.createBoundary();
		             URLConnection urlConn = MultiPartFormOutputStream.createConnection(url);
		             urlConn.setReadTimeout(15000);
		             urlConn.setRequestProperty("Accept", "*/*");
		             urlConn.setRequestProperty("Content-Type", MultiPartFormOutputStream.getContentType(boundary));
		             
		             urlConn.setRequestProperty("Connection", "Keep-Alive");
		             urlConn.setRequestProperty("Cache-Control", "no-cache");
		             
		             MultiPartFormOutputStream out = new MultiPartFormOutputStream(urlConn.getOutputStream(), boundary);
		             out.writeField("param", "value");
		             out.writeFile("myFile", "text/plain", new File("C:/test.txt"));
		             out.close();
		            
					}catch(IOException e){
						e.printStackTrace();
					}
		
		
	}
	
	@GET
	@Path("/text")
	@Produces({"application/xml","application/json"})
	public List<ItemMarshaller> getReports(){
		List<Report> reps = new ArrayList<Report>();
		List<ItemMarshaller> items = new ArrayList<ItemMarshaller>();
		reps = Dao.INSTANCE.getReports("niro");
		for (Report rep : reps) {
	     System.out.println("Summary "+rep.getShortDescription());
	     System.out.println("Description "+rep.getLongDescription());
	     System.out.println("URL "+rep.getUrl());
	     System.out.println("----------");
	     
	     ItemMarshaller item = new ItemMarshaller();
	     item.setSummary(rep.getShortDescription());
	     item.setDescription(rep.getLongDescription());
	     item.setUrl(rep.getUrl());
	     items.add(item);
		}
		return items;
	}
	
	

}
