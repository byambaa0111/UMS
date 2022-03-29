package ums

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

class LocaleNavbarTagLib implements GrailsConfigurationAware {

    static namespace = 'navBar'

    static defaultEncodeAs = [taglib: 'none']

    MessageSource messageSource

    List<String> languages

    @Override
    void setConfiguration(Config co) {
        languages = co.getProperty('guide.languages', List)
    }
    def localeDropdownListItems = { args ->
        String uri = args.uri
        for (String lang : languages) {
            String languageCode = "language.$lang"

            def locale = LocaleContextHolder.locale
            def msg = messageSource.getMessage(languageCode, [] as Object[], null, locale)
            //out << "<li><a href='${uri}?lang=${lang}'>${msg}</a></li>"
             out<< "<a href='${uri}?lang=${lang}' class='dropdown-item d-flex align-items-center'><asset:image rel='icon' alt='image' src='flags/${msg}.jpg' alt='flag-img' class=' flag-sm mr-3 align-self-center'/><div>"
             out<< "<strong>${msg}</strong>"
             out<< "</div>     </a>"
        }
    }
}