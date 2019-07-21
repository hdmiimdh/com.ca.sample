package com.ca.sample.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessagesUtils {

    private final MessageSource messageSource;

    @Autowired
    public MessagesUtils(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final String code, final Object... args) {
        try {
            return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "Unresolved code: " + code;
        }
    }
}