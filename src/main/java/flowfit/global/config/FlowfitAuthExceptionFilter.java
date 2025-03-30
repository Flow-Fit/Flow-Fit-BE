//package flowfit.global.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.filter.GenericFilterBean;
//
//import java.io.IOException;
//import java.util.Map;
//
//public class FlowfitAuthExceptionFilter extends GenericFilterBean {
//
//    private final ObjectMapper objectMapper;
//
//    public FlowfitAuthExceptionFilter(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        try {
//            chain.doFilter(request, response);
//        } catch (RuntimeException ex) { // 예외를 잡아냄
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            httpResponse.setContentType("application/json; charset=UTF-8");
//
//            Map<String, String> errorResponse = Map.of(
//                    "error", "BadRequest",
//                    "message", ex.getMessage()
//            );
//
//            String json = objectMapper.writeValueAsString(errorResponse);
//            httpResponse.getWriter().write(json);
//        }
//    }
//}
