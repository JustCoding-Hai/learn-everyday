package com.learn.securitydy.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.Collection;

/**
 * @author Hai
 * @date 2020/4/11 - 23:27
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
  /**
   *
   * @param authentication 具有的角色
   * @param o  当前访问对象
   * @param collection 需要的角色
   * @throws AccessDeniedException
   * @throws InsufficientAuthenticationException
   */
  @Override
  public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
    for (ConfigAttribute attribute : collection) {
      if ("ROLE_login".equals(attribute.getAttribute())){
        if (authentication instanceof AnonymousAuthenticationToken){
          throw new AccessDeniedException("非法请求！");
        }
        else {
          return;
        }
      }
      /*
      这里判断当前用户是否拥有访问特定路径所需要的角色，具有一个皆可或都需要
       */
      Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
      for (GrantedAuthority authority : authorities) {
        if (authority.getAuthority().equals(attribute.getAttribute())){
          return;
        }
      }
    }
     throw new AccessDeniedException("非法请求！");
  }

  @Override
  public boolean supports(ConfigAttribute configAttribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
