 package market.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Action;
import common.ActionForward;
import market.svc.OpenMarketProService;
import market.vo.MarketBean;


public class OpenMarketProAction implements Action {

	@Override
	public common.ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		ServletContext context = request.getServletContext();
		String saveFolder = "/upload";
		String realFolder = context.getRealPath(saveFolder);
			
		
		int fileSize = 1024*1024*10;
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String isupdate = multi.getParameter("isupdate");
		
		MarketBean profile = new MarketBean();
		profile.setMb_id(multi.getParameter("mb_id"));
		
		profile.setMp_introduce(multi.getParameter("mp_Introduce"));
		profile.setTn_name(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		profile.setTn_source_name(multi.getFilesystemName("tn_name"));

		OpenMarketProService service = new OpenMarketProService();
		
		if(isupdate.equals("insert")) {
			boolean isInsertedSuccess = service.UpdateProfile(profile);
			forward = new ActionForward();
			
			if(!isInsertedSuccess) {
				request.setAttribute("isSuccess", 3); 
				forward.setPath("/mypage/OpenMarketForm.mk");
			}else {
				request.setAttribute("isSuccess", 1);
				forward.setPath("/mypage/open_market.jsp");
			}
		
		}else if(isupdate.equals("update")){
			boolean isUpdatedSuccess = service.UpdateProfile(profile);
			forward = new ActionForward();
			
			if(!isUpdatedSuccess) {
				request.setAttribute("isSuccess", 3);
				forward.setPath("/mypage/OpenMarketForm.mk");
			}else {
				request.setAttribute("isSuccess", 2);
				forward.setPath("/mypage/open_market.jsp");
			}
		}
		return forward;
	}
}
