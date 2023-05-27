package example.test.springbootmultidb.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class ThreadContextFilter extends OncePerRequestFilter {

	private static final String REFERENCE_ID = "ReferenceID";
	private static final String X_REFERENCE_ID = "X-Reference-ID";
	private static final String X_CORRELATION_ID = "X-Correlation-ID";
	private static final String X_CLIENT_ID = "X-Client-ID";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ThreadContext.put("ReferenceID", UUID.randomUUID().toString());
		
		HttpServletResponse wrapper = new HttpServletResponseWrapper((HttpServletResponse) response) {
			
			@Override
			public void setHeader(String name, String value) {
				if(!(name.equalsIgnoreCase("X-Application-Context") 
						|| name.equalsIgnoreCase("X-Powered-by"))) {
					super.setHeader(name, value);
				}
			}
			
			@Override
			public void setStatus(int sc) {
				super.setStatus(sc);
				addHeaders();
			}
			
			@Override
			public void sendError(int sc, String msg) throws IOException {
				super.sendError(sc, msg);
				addHeaders();
			}
			
			@Override
			public void sendError(int sc) throws IOException {
				super.sendError(sc);
				addHeaders();
			}

			private void addHeaders() {
				HttpServletResponse resp = (HttpServletResponse) response;
				HttpServletRequest req = (HttpServletRequest) request;
				
				resp.setHeader(X_CLIENT_ID, req.getHeader(X_CLIENT_ID));
				resp.setHeader(X_CORRELATION_ID, req.getHeader(X_CORRELATION_ID));
				resp.setHeader(X_REFERENCE_ID, ThreadContext.get(REFERENCE_ID));
			}
		};

		filterChain.doFilter(request, wrapper);
		ThreadContext.clearAll();

	}

}
