package item.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Action;
import common.ActionForward;
import item.svc.ItemWriteService;
import item.vo.ItemBean;
import member.vo.MemberBean;

public class ItemWriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		
		if(memberID == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath("/upload");
		
		int fileSize = 1024 * 1024 * 30;
		String filename = "";
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			filename = mr.getOriginalFileName("files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 썸네일 이미지가 등록되지 않았을 때
		if(filename == null) {
			out.println("<script>");
			out.println("alert('대표 이미지가 등록되지 않았습니다.\\n썸네일 이미지는 내용 마지막에 삽입된 이미지가 썸네일로 지정됩니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		ParameterBlock pb = new ParameterBlock();
		pb.add(realFolder + "/" + filename);
		RenderedOp rop = JAI.create("fileload", pb);
		
		BufferedImage bi = rop.getAsBufferedImage();
		BufferedImage thumb = new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2 = thumb.createGraphics();
		g2.drawImage(bi, 0, 0, 120, 120, null);
		
		File file = new File(realFolder, "/sm_" + filename);
		ImageIO.write(thumb, "jpg", file);
		
		// 입력받은 콤마를 공백으로 치환
		int priceStart = Integer.parseInt(mr.getParameter("price_start").replace(",", ""));
		int priceMax = Integer.parseInt(mr.getParameter("price_end").replace(",", ""));
		
		if(priceStart >= priceMax) {
			out.println("<script>");
			out.println("alert('시작가가 낙찰가보다 같거나 높을 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return forward;
		}
		
		int ticket = 0;
		ItemWriteService iws = null;
		
		// 땅땅티켓 사용했을 때
		if(mr.getParameter("ticket") != null) {
			ticket = Integer.parseInt(mr.getParameter("ticket"));
			
			iws = new ItemWriteService();
			MemberBean ticketCount = iws.selectTicket(memberID);
			
			if(ticketCount.getTicket() == 0) {
				out.println("<script>");
				out.println("alert('땅땅티켓을 보유하고 있지 않아 광고 상품으로 등록하실 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				
				return forward;
			}
			
			// 땅땅티켓 1개 소모
			iws.useTicket(memberID);
		}
		
		ItemBean itemBean = new ItemBean();
		itemBean.setName(mr.getParameter("subject"));
		itemBean.setWriteTime(new Timestamp(System.currentTimeMillis()));
		itemBean.setEndTime(mr.getParameter("datetime_end"));
		itemBean.setStartPrice(priceStart);
		itemBean.setNowPrice(priceStart); // 시작가와 현재가 동일하게 들어가야함
		itemBean.setMaxPrice(priceMax);
		itemBean.setDeliveryPrice(Integer.parseInt(mr.getParameter("price_delivery").replace(",", "")));
		itemBean.setContent(mr.getParameter("content"));
		itemBean.setCategory(mr.getParameter("category"));
		itemBean.setMemberID(memberID);
		itemBean.setThumbnail("sm_" + filename);
		itemBean.setIsTicket(ticket);

		// 글 등록 요청
		if(iws == null) iws = new ItemWriteService();
		boolean isSuccess = iws.registArticle(itemBean);
		
		if(!isSuccess) {
			out.println("<script>");
			out.println("alert('상품 등록에 문제가 발생하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("list.it");
		}

		return forward;
	}

}
