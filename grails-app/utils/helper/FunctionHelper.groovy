
package helper

import com.ums.system.SysUser
import grails.core.GrailsApplication
import grails.util.Holders
import grails.web.Action
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory

/**
 *
 * @author bb
 */
class FunctionHelper {

    GrailsApplication  grailsApplication = Holders.grailsApplication
    
    boolean transactional = true;
    public  static final String CASHER = "Касс"
    public  static final String SCHOOLERSHIP = "Төрийн сан"
    public  static final String SEVENTY200 = "70-200"
    public  static final String FIRSTBALANCE = "Эхний үлдэгдэл"
    public  static final String DISCOUNT = "Төлбөрийн хөнгөлөлт"

    private String getStudentsMark(float percent) {
        println("[get mark ]"+percent)

        if (69 >= percent && percent > 59) {
            return "D"
        }
        if (79 >= percent && percent > 69) {
            return "C"
        }
        if (89 >= percent && percent > 79) {
            return "B"
        }
        if ( percent > 89) {
            return "A"
        }

        return "F";
    }
    private float getCalcGPA(grade){
        float GPA=0;

        switch (grade) {
            case 100:  GPA = 4;    break;
            case 99:  GPA = 4;     break;
            case 98:  GPA = 4;     break;
            case 97:  GPA = 3.9;   break;
            case 96:  GPA = 3.8;   break;
            case 95:  GPA = 3.7;   break;
            case 94:  GPA = 3.6;   break;
            case 93:  GPA = 3.6;   break;
            case 92:  GPA = 3.5;   break;
            case 91:  GPA = 3.5;   break;
            case 90:  GPA = 3.4;   break;
            case 89:  GPA = 3.3;   break;
            case 88:  GPA = 3.3;   break;
            case 87:  GPA = 3.2;   break;
            case 86:  GPA = 3.1;   break;
            case 85:  GPA = 3.1;   break;
            case 84:  GPA = 3;     break;
            case 83:  GPA = 2.9;   break;
            case 82:  GPA = 2.8;   break;
            case 81:  GPA = 2.7;   break;
            case 80:  GPA = 2.6;   break;
            case 79:  GPA = 2.5;   break;
            case 78:  GPA = 2.4;   break;
            case 77:  GPA = 2.3;   break;
            case 76:  GPA = 2.2;   break;
            case 75:  GPA = 2.2;   break;
            case 74:  GPA = 2;     break;
            case 73:  GPA = 1.9;   break;
            case 72:  GPA = 1.8;   break;
            case 71:  GPA = 1.7;   break;
            case 70:  GPA = 1.6;   break;
            case 69:  GPA = 1.5;   break;
            case 68:  GPA = 1.4;   break;
            case 67:  GPA = 1.3;   break;
            case 66:  GPA = 1.2;   break;
            case 65:  GPA = 1.1;   break;
            case 64:  GPA = 1;     break;

            default: GPA = 0;
                break;
        }

        return GPA;
    }
    private Map getGetSumList(){
        def data = [:]

        def district1 = []
        district1.add("Багануур	4                                                   ")
        district1.add("Багахангай	2                                               ")
        district1.add("Баянгол	 1/22                                               ")
        district1.add("Баянзүрх	1/28                                                ")
        district1.add("Налайх	6                                                   ")
        district1.add("Сонгинохайрхан	32                                          ")
        district1.add("Сүхбаатар	20                                                  ")
        district1.add("Хан-Уул	 16                                                 ")
        district1.add("Чингэлтэй 18                                                  ")
        def district2 = []
        district2.add("Архангай аймаг  65130  Эрдэнэбулган сум ")
        district2.add("Архангай аймаг  65010  Хашаат сум                             ")
        district2.add("Архангай аймаг  65020  Өгийнуур сум                           ")
        district2.add("Архангай аймаг  65030  Өлзийт сум                             ")
        district2.add("Архангай аймаг  65040  Хотонт сум                             ")
        district2.add("Архангай аймаг  65060  Хайрхан сум                            ")
        district2.add("Архангай аймаг  65070  Төвшрүүлэх сум                         ")
        district2.add("Архангай аймаг  65080  Цэнхэр сум                             ")
        district2.add("Архангай аймаг  65100  Батцэнгэл сум  Батцэнгэл сумын ШҮС     ")
        district2.add("Архангай аймаг  65120  Булган сум  Булган сумын ШҮС           ")
        district2.add("Архангай аймаг  65150  Эрдэнэмандал сум                       ")
        district2.add("Архангай аймаг  65170  Ихтамир сум                            ")
        district2.add("Архангай аймаг  65190  Цэцэрлэг сум                           ")
        district2.add("Архангай аймаг  65200  Жаргалант сум                          ")
        district2.add("Архангай аймаг  65210  Өндөр-Улаан сум                        ")
        district2.add("Архангай аймаг  65220  Чулуут сум                             ")
        district2.add("Архангай аймаг  65230  тариат сум                             ")
        district2.add("Архангай аймаг  65250  Хангай сум                             ")
        district2.add("Архангай аймаг  65270  Цахир сум                       ")

        def district3 = []
        district3.add("Баян-Өлгий аймаг  83120  Өлгий сум                            ")
        district3.add("Баян-Өлгий аймаг  83010  Булган сум                           ")
        district3.add("Баян-Өлгий аймаг  83030  Баяннуур сум                         ")
        district3.add("Баян-Өлгий аймаг  83040  Алтанцөгц сум                        ")
        district3.add("Баян-Өлгий аймаг  83050  Бугат сум                            ")
        district3.add("Баян-Өлгий аймаг  83060  Дэлүүн сум                           ")
        district3.add("Баян-Өлгий аймаг  83100  Ногооннуур сум                       ")
        district3.add("Баян-Өлгий аймаг  83140  Буянт сум                            ")
        district3.add("Баян-Өлгий аймаг  83140  Алтай сум                            ")
        district3.add("Баян-Өлгий аймаг  83160  Сагсай сум                           ")
        district3.add("Баян-Өлгий аймаг  83170  Улаанхус сум                         ")
        district3.add("Баян-Өлгий аймаг  83190  Цэнгэл сум                           ")

        def district4 = []
        district4.add("Баянхонгор аймаг  64090  Баянхонгор сум                       ")
        district4.add("Баянхонгор аймаг  64010  Баянлиг сум                          ")
        district4.add("Баянхонгор аймаг  64020  Эрдэнэцогт сум                       ")
        district4.add("Баянхонгор аймаг  64030  Өлзийт сум                           ")
        district4.add("Баянхонгор аймаг  64050  Богд сум                             ")
        district4.add("Баянхонгор аймаг  64070  Жинст сум                            ")
        district4.add("Баянхонгор аймаг  64080  Баян-Овоо сум                        ")
        district4.add("Баянхонгор аймаг  64110  Галуут сум                           ")
        district4.add("Баянхонгор аймаг  64113  Баянговь сум                         ")
        district4.add("Баянхонгор аймаг  64150  Баацагаан сум                        ")
        district4.add("Баянхонгор аймаг  64170  Бөмбөгөр сум                         ")
        district4.add("Баянхонгор аймаг  64180  Жаргалант сум                        ")
        district4.add("Баянхонгор аймаг  64200  Заг сум                              ")
        district4.add("Баянхонгор аймаг  64210  Баянцагаан сум                       ")
        district4.add("Баянхонгор аймаг  64230  Гурванбулаг сум                      ")
        district4.add("Баянхонгор аймаг  64240  Хүрээмандал сум                      ")
        district4.add("Баянхонгор аймаг  64250  Баян-Өндөр сум                       ")
        district4.add("Баянхонгор аймаг  64260  Баянбулаг сум                        ")
        district4.add("Баянхонгор аймаг  83080  Толбо сум                            ")
        def district5 = []
        district5.add("Булган аймаг  63080  Булган сум                               ")
        district5.add("Булган аймаг  63010  Баяннуур сум                             ")
        district5.add("Булган аймаг  63020  Сэлэнгэ сум                              ")
        district5.add("Булган аймаг  63030  Хангал сум                               ")
        district5.add("Булган аймаг  63040  Орхон сум                                ")
        district5.add("Булган аймаг  63050  Бүрэнхангай сум                          ")
        district5.add("Булган аймаг  63060  Дашинчилэн сум                           ")
        district5.add("Булган аймаг  63070  Рашаант сум                              ")
        district5.add("Булган аймаг  63090  Бугат сум                                ")
        district5.add("Булган аймаг  63100  Тэшиг сум                                ")
        district5.add("Булган аймаг  63110  Хишиг-Өндөр сум                          ")
        district5.add("Булган аймаг  63120  Гурванбулаг сум                          ")
        district5.add("Булган аймаг  63130  Хутаг-Өндөр сум                          ")
        district5.add("Булган аймаг  63150  Могод сум                                ")
        district5.add("Булган аймаг  63160  Сайхан сум                               ")
        district5.add("Булган аймаг  63180  Баян-Агт сум                             ")

        def district6 = []
        district6.add("Говь-Алтай аймаг  82080  Есөнбулаг сум                        ")
        district6.add("Говь-Алтай аймаг  820810  Дэлгэр сум                          ")
        district6.add("Говь-Алтай аймаг  82020  Чандмань сум                         ")
        district6.add("Говь-Алтай аймаг  82030  Эрдэнэ сум                           ")
        district6.add("Говь-Алтай аймаг  82040  Бигэр сум                            ")
        district6.add("Говь-Алтай аймаг  82050  Цогт сум                             ")
        district6.add("Говь-Алтай аймаг  82070  Тайшир сум                           ")
        district6.add("Говь-Алтай аймаг  82080  Халиун сум                           ")
        district6.add("Говь-Алтай аймаг  82100  Хариун сум                           ")
        district6.add("Говь-Алтай аймаг  82110  Жаргалан сум                         ")
        district6.add("Говь-Алтай аймаг  82120  Цээл сум                             ")
        district6.add("Говь-Алтай аймаг  82130  Алтай сум                            ")
        district6.add("Говь-Алтай аймаг  82140  Шарга сум                            ")
        district6.add("Говь-Алтай аймаг  82150  Баян-Уул сум                         ")
        district6.add("Говь-Алтай аймаг  82170  Төгрөг сум                           ")
        district6.add("Говь-Алтай аймаг  82180  Хөхморьт сум                         ")
        district6.add("Говь-Алтай аймаг  82190  Дарви сум                            ")
        district6.add("Говь-Алтай аймаг  82200  Бугат сум                            ")
        district6.add("Говь-Алтай аймаг  82210  Тонхил сум                           ")
        def district7 = []
        district7.add("Говьсүмбэр аймаг  45010  Сүмбэр сум                           ")
        district7.add("Говьсүмбэр аймаг  42020  Шивээговь сум                        ")
        district7.add("Говьсүмбэр аймаг  42030  Баянтал сум                          ")
        def district8 = []
        district8.add("Дархан-Уул аймаг 45040 	Дархан сум                          ")
        district8.add("Дархан-Уул аймаг 45010 Шарын гол сум                          ")
        district8.add("Дархан-Уул аймаг 45020 Хонгор сум                             ")
        district8.add("Дархан-Уул аймаг 45030 Орхон сум                              ")
        def district9 = []
        district9.add("Дорноговь аймаг  44100  Сайншанд сум                          ")
        district9.add("Дорноговь аймаг  44010  Замын-Үүд сум                         ")
        district9.add("Дорноговь аймаг  44020  Эрдэнэ сум                            ")
        district9.add("Дорноговь аймаг  44030  Өргөн сум                             ")
        district9.add("Дорноговь аймаг  44050  Дэлгэрэх сум                          ")
        district9.add("Дорноговь аймаг  44050  Улаанбадрах сум                       ")
        district9.add("Дорноговь аймаг  44060  Хөвсгөл сум                           ")
        district9.add("Дорноговь аймаг  44070  Алтанширээ сум                        ")
        district9.add("Дорноговь аймаг  44080  Хатанбулаг сум                        ")
        district9.add("Дорноговь аймаг  44090  Иххэт сум                             ")
        district9.add("Дорноговь аймаг  44110  Сайхандулаан сум                      ")
        district9.add("Дорноговь аймаг  44120  Айраг сум                             ")
        district9.add("Дорноговь аймаг  44130  Даланжаргалан сум                     ")
        district9.add("Дорноговь аймаг  44140  Мандах сум                            ")
        def district10 = []
        district10.add("Дорнод аймаг  21060  Хэрлэн сум                               ")
        district10.add("Дорнод аймаг  21010  Халхгол сум                              ")
        district10.add("Дорнод аймаг  21020  Матад сум                                ")
        district10.add("Дорнод аймаг  21030  Чулуун хороот сум                        ")
        district10.add("Дорнод аймаг  21040  Чойбалсан сум                            ")
        district10.add("Дорнод аймаг  21050  Баянтүмэн сум                            ")
        district10.add("Дорнод аймаг  21080  Гурванзагал сум                          ")
        district10.add("Дорнод аймаг  21090  Булган сум                               ")
        district10.add("Дорнод аймаг  21100  Дашбалбар сум                            ")
        district10.add("Дорнод аймаг  21110  Сэргэлэн сум                             ")
        district10.add("Дорнод аймаг  21120  Баяндун сум                              ")
        district10.add("Дорнод аймаг  21130  Цагаан-Оюоо сум                          ")
        district10.add("Дорнод аймаг  21150  Хөлөнбуйр сум                            ")
        district10.add("Дорнод аймаг  21160  Баян-Уул сум                             ")
        def district11 = []
        district11.add("Дундговь аймаг  48090  Сайнцагаан сум                         ")
        district11.add("Дундговь аймаг  48010  Өндөршил сум                           ")
        district11.add("Дундговь аймаг  48020  Баянжаргалан сум                       ")
        district11.add("Дундговь аймаг  48030  Цагаандэлгэр сум                       ")
        district11.add("Дундговь аймаг  48040  Өлзийт сум                             ")
        district11.add("Дундговь аймаг  48060  Гурвансайхан сум                       ")
        district11.add("Дундговь аймаг  48070  Говь-Угтаал сум                        ")
        district11.add("Дундговь аймаг  48080  Дэрэн сум                              ")
        district11.add("Дундговь аймаг  48110  Дундговь аймаг                         ")
        district11.add("Дундговь аймаг  48120  Луус сум                               ")
        district11.add("Дундговь аймаг  48130  Хулд сум                               ")
        district11.add("Дундговь аймаг  48140  Адаацаг сум                            ")
        district11.add("Дундговь аймаг  48150  Эрдэнэдалай сум                        ")
        district11.add("Дундговь аймаг  48170  Дэлгэрхангай сум                       ")
        district11.add("Дундговь аймаг  48180  Сайхан овоо сум                        ")
        def district12 = []
        district12.add("Завхан аймаг  81090  Улиастай сум                             ")
        district12.add("Завхан аймаг  81010  Их-Уул сум                               ")
        district12.add("Завхан аймаг  81030  Тосонцэнгэл сум                          ")
        district12.add("Завхан аймаг  81050  Тэлмэн сум                               ")
        district12.add("Завхан аймаг  81060  Идэр сум                                 ")
        district12.add("Завхан аймаг  81070  Отгон сум                                ")
        district12.add("Завхан аймаг  81110  Нөмрөг сум                               ")
        district12.add("Завхан аймаг  81120  Цагаанхайрхан сум                        ")
        district12.add("Завхан аймаг  81130  Яруу сум                                 ")
        district12.add("Завхан аймаг  81140  Шилүүстэй сум                            ")
        district12.add("Завхан аймаг  81150  Цагаанчулуут сум                         ")
        district12.add("Завхан аймаг  81160  Баянтэс сум                              ")
        district12.add("Завхан аймаг  81170  Асгат сум                                ")
        district12.add("Завхан аймаг  81180  Баянрхайрхан сум                         ")
        district12.add("Завхан аймаг  81190  Алдархаан сум                            ")
        district12.add("Завхан аймаг  81210  Түвэвтэй сум                             ")
        district12.add("Завхан аймаг  81220  Цэцэн-Уул сум                            ")
        district12.add("Завхан аймаг  81230  Сонгино сум                              ")
        district12.add("Завхан аймаг  81240  Эрдэнэхайрхан сум                        ")
        district12.add("Завхан аймаг  81260  Сантмаргад сум                           ")
        district12.add("Завхан аймаг  81270  Завханмандал сум                         ")
        district12.add("Завхан аймаг  81280  Дөрвөлжин сум                            ")
        district12.add("Завхан аймаг  81290  Ургамал сум                              ")
        district12.add("Завхан аймаг  81250  Тэс сум                                  ")
        def district13 = []

        district13.add("Өвөрхангай аймаг  62160  Арвай хээр сум                       ")
        district13.add("Өвөрхангай аймаг  62010  Баян-Өндөр сум                       ")
        district13.add("Өвөрхангай аймаг  62020  Бүрд сум                             ")
        district13.add("Өвөрхангай аймаг  62030  Есонзүйл сум                         ")
        district13.add("Өвөрхангай аймаг  62040  Сант сум                             ")
        district13.add("Өвөрхангай аймаг  62050  Өлзийнт сум                          ")
        district13.add("Өвөрхангай аймаг  62060  Баянгол сум                          ")
        district13.add("Өвөрхангай аймаг  62080  Төгрөг сум                           ")
        district13.add("Өвөрхангай аймаг  62090  Хархорин сум                         ")
        district13.add("Өвөрхангай аймаг  62110  Хужирт сум                           ")
        district13.add("Өвөрхангай аймаг  62120  Зүүнбаян-Улаан сумм                  ")
        district13.add("Өвөрхангай аймаг  62140  Тарагт сум                           ")
        district13.add("Өвөрхангай аймаг  62180  Гучин ус сум                         ")
        district13.add("Өвөрхангай аймаг  62190  Богд сум                             ")
        district13.add("Өвөрхангай аймаг  62210  Бат-Өлзийт сум                       ")
        district13.add("Өвөрхангай аймаг  62220  Уянга сум                            ")
        district13.add("Өвөрхангай аймаг  62240  Хайрхандулаан сум                    ")
        district13.add("Өвөрхангай аймаг  62260  Нарийнтээл сум                       ")
        district13.add("Өвөрхангай аймаг  62270  Баруунбаян-Улаан сум                 ")
        def district14 = []
        district14.add("Өмнөговь аймаг  46080  Даланзадгад сум                        ")
        district14.add("Өмнөговь аймаг  46010  Ханбогд сум                            ")
        district14.add("Өмнөговь аймаг  46020  Манлай сум                             ")
        district14.add("Өмнөговь аймаг  46030  Баян-Овоо сум                          ")
        district14.add("Өмнөговь аймаг  46040  Цогтцэций сум                          ")
        district14.add("Өмнөговь аймаг  46050  Номгон сум                             ")
        district14.add("Өмнөговь аймаг  46060  Цогт-Овоо сум                          ")
        district14.add("Өмнөговь аймаг  46070  Ханхонхор сум                          ")
        district14.add("Өмнөговь аймаг  46090  Мандал-Овоо сум                        ")
        district14.add("Өмнөговь аймаг  46100  Хүрмэн сум                             ")
        district14.add("Өмнөговь аймаг  46110  Булган сум                             ")
        district14.add("Өмнөговь аймаг  46120  Баяндалай сум                          ")
        district14.add("Өмнөговь аймаг  46130  Сэврэй сум                             ")
        district14.add("Өмнөговь аймаг  46140  Ноён сум                               ")
        district14.add("Өмнөговь аймаг  46150  Гурвантэс сум                          ")
        def district15 = []
        district15.add("Сүхбаатар аймаг  22070  Баруун-Урт сум                        ")
        district15.add("Сүхбаатар аймаг  22010  Эрдэнэцагаан сум                      ")
        district15.add("Сүхбаатар аймаг  22030  Сүхбаатар сум                         ")
        district15.add("Сүхбаатар аймаг  22040  Дарьганга сум                         ")
        district15.add("Сүхбаатар аймаг  22050  Асгат сум                             ")
        district15.add("Сүхбаатар аймаг  22060  Наран сум                             ")
        district15.add("Сүхбаатар аймаг  22090  Онгон сум                             ")
        district15.add("Сүхбаатар аймаг  22100  Халзан сум                            ")
        district15.add("Сүхбаатар аймаг  22110  Баяндэлгэр сум                        ")
        district15.add("Сүхбаатар аймаг  22130  Уулбаян сум                           ")
        district15.add("Сүхбаатар аймаг  22140  Мөнххаан сум                          ")
        district15.add("Сүхбаатар аймаг  22150  Түмэнцогт сум                         ")
        district15.add("Сүхбаатар аймаг  22160  Түвшинширээ сум                       ")
        def district16 = []
        district16.add("Сэлэнгэ аймаг  43080  Сүхбаатар сум                           ")
        district16.add("Сэлэнгэ аймаг  43010  Ерөө сум                                ")
        district16.add("Сэлэнгэ аймаг  43020  Хүдэр сум                               ")
        district16.add("Сэлэнгэ аймаг  43030  Мандал сум                              ")
        district16.add("Сэлэнгэ аймаг  43040  Алтанбулаг сум                          ")
        district16.add("Сэлэнгэ аймаг  43050  Жавхлант сум                            ")
        district16.add("Сэлэнгэ аймаг  43060  Баянгол сум                             ")
        district16.add("Сэлэнгэ аймаг  43070  Шаамар сум                              ")
        district16.add("Сэлэнгэ аймаг  43090  Зүүнбүрэн сум                           ")
        district16.add("Сэлэнгэ аймаг  43100  Түшиг сум                               ")
        district16.add("Сэлэнгэ аймаг  43110  Цагааннуур сум                          ")
        district16.add("Сэлэнгэ аймаг  43120  Хушаат сум                              ")
        district16.add("Сэлэнгэ аймаг  43130  Сайхан сум                              ")
        district16.add("Сэлэнгэ аймаг  43140  Орхон сум                               ")
        district16.add("Сэлэнгэ аймаг  43150  Сант сум                                ")
        district16.add("Сэлэнгэ аймаг  43160  Орхонтуул сум                           ")
        district16.add("Сэлэнгэ аймаг  43170  Баруунбүрэн сум                         ")
        def district17 = []
        district17.add("Төв аймаг  41100  Зуунмод сум                                 ")
        district17.add("Төв аймаг  41010  Мөнгөнморьт сум                             ")
        district17.add("Төв аймаг  41020  Баянжаргалан сум                            ")
        district17.add("Төв аймаг  41030  Баяндэлгэр сум                              ")
        district17.add("Төв аймаг  41040  Эрдэнэ сум                                  ")
        district17.add("Төв аймаг  41060  Архуст сум                                  ")
        district17.add("Төв аймаг  41070  Баян сум                                    ")
        district17.add("Төв аймаг  41080  Баянцагаан сум                              ")
        district17.add("Төв аймаг  41090  Сэргэлэн сум                                ")
        district17.add("Төв аймаг  41120  Батсүмбэр сум                               ")
        district17.add("Төв аймаг  41130  Алтанбулаг сум                              ")
        district17.add("Төв аймаг  41140  Өнжүүл сум                                  ")
        district17.add("Төв аймаг  41150  Борнуур сум                                 ")
        district17.add("Төв аймаг  41160  Баянчандмань сум                            ")
        district17.add("Төв аймаг  41170  Баянцогт сум                                ")
        district17.add("Төв аймаг  41180  Аргалант сум                                ")
        district17.add("Төв аймаг  41190  Сүмбэр сум                                  ")
        district17.add("Төв аймаг  41200  Жаргалант сум                               ")
        district17.add("Төв аймаг  41210  Баянхангай сум                              ")
        district17.add("Төв аймаг  41220  Угтаалцайдам сум Төв аймаг  41230  Цээл сум ")
        district17.add("Төв аймаг  41240  Лүн сум                                     ")
        district17.add("Төв аймаг  41250  Өндөрширээт сум                             ")
        district17.add("Төв аймаг  41260  Бүрэн сум                                   ")
        district17.add("Төв аймаг  41270  Заамар сум                                  ")
        district17.add("Төв аймаг  41280  Дэлгэрхаан сум                              ")
        district17.add("Төв аймаг  41290  Эрэдэнсант сум                              ")
        def district18 = []
        district18.add("Увс аймаг  85160  Улаангом сум                                ")
        district18.add("Увс аймаг  85010  Зүүнхангай сум                              ")
        district18.add("Увс аймаг  85020  Баруунтуруун сум                            ")
        district18.add("Увс аймаг  85030  Өндөрхангай сум                             ")
        district18.add("Увс аймаг  85040  Зүүнговь сум                                ")
        district18.add("Увс аймаг  85050  Цагаанхайрхан сум                           ")
        district18.add("Увс аймаг  85060  Тэс сум                                     ")
        district18.add("Увс аймаг  85080  Хяргас сум                                  ")
        district18.add("Увс аймаг  85090  Малчин сум                                  ")
        district18.add("Увс аймаг  85100  Завхан сум                                  ")
        district18.add("Увс аймаг  85110  Наранбулаг сум                              ")
        district18.add("Увс аймаг  85120  Давст сум                                   ")
        district18.add("Увс аймаг  85130  Тариалан сум                                ")
        district18.add("Увс аймаг  85150  Өлгий сум                                   ")
        district18.add("Увс аймаг  85190  Сагил сум                                   ")
        district18.add("Увс аймаг  85210  Өмнөговь сум                                ")
        district18.add("Увс аймаг  85220  Бөхмөрөн сум                                ")
        district18.add("Увс аймаг  85230  Ховд сум                                    ")
        def district19 = []
        district19.add("Ховд аймаг  84140  Жаргалант сум                              ")
        district19.add("Ховд аймаг  84010  Дарви сум                                  ")
        district19.add("Ховд аймаг  84020  Дөргөн сум                                 ")
        district19.add("Ховд аймаг  84030  Чандмань сум                               ")
        district19.add("Ховд аймаг  84050  Цэцэг сум                                  ")
        district19.add("Ховд аймаг  84060  Зэрэг сум                                  ")
        district19.add("Ховд аймаг  84070  Мөст сум                                   ")
        district19.add("Ховд аймаг  84080  Алтай сум                                  ")
        district19.add("Ховд аймаг  84090  Буянт сум                                  ")
        district19.add("Ховд аймаг  84100  Мянгад сум                                 ")
        district19.add("Ховд аймаг  48110  Манхан сум                                 ")
        district19.add("Ховд аймаг  84130  Үенч сум                                   ")
        district19.add("Ховд аймаг  84170  Ховд сум                                   ")
        district19.add("Ховд аймаг  84180  Эрдэнэбүрэн сум                            ")
        district19.add("Ховд аймаг  84190  Мөнххайрхан сум                            ")
        district19.add("Ховд аймаг  84210  Булган сум                                 ")
        def district20 = []
        district20.add("Хөвсгөл аймаг  67120  Мөрөн сум                               ")
        district20.add("Хөвсгөл аймаг  67010  Тариалан сум                            ")
        district20.add("Хөвсгөл аймаг  67030  Эрдэнэбулган сум                        ")
        district20.add("Хөвсгөл аймаг  67040  Цагаан-Үүр сум                          ")
        district20.add("Хөвсгөл аймаг  67050  Их-Уул сум                              ")
        district20.add("Хөвсгөл аймаг  67060  Рашаант сум                             ")
        district20.add("Хөвсгөл аймаг  67060  Рашаант сум                             ")
        district20.add("Хөвсгөл аймаг  67070  Ханх сум                                ")
        district20.add("Хөвсгөл аймаг  67080  Чандмань өндөр сум                      ")
        district20.add("Хөвсгөл аймаг  67090  Түнэл сум                               ")
        district20.add("Хөвсгөл аймаг  67110  Тосонцэнгэл сум                         ")
        district20.add("Хөвсгөл аймаг  67140  Алаг-Эрдэнэ сум                         ")
        district20.add("Хөвсгөл аймаг  67160  Төмөрбулаг сум                          ")
        district20.add("Хөвсгөл аймаг  67170  Рэнчинлүмбэ сум                         ")
        district20.add("Хөвсгөл аймаг  67190  Галт сум                                ")
        district20.add("Хөвсгөл аймаг  67200  Арбулаг сум                             ")
        district20.add("Хөвсгөл аймаг  67220  Бүрэнтогтох сум                         ")
        district20.add("Хөвсгөл аймаг  67240  Шинэ-Идэр сум                           ")
        district20.add("Хөвсгөл аймаг  67250  Жаргалант сум                           ")
        district20.add("Хөвсгөл аймаг  67260  Цагааннуур сум                          ")
        def district21 = []
        district21.add("Хэнтий аймаг  23110  Хэрлэн сум                               ")
        district21.add("Хэнтий аймаг  23010  Баян-Овоо сум                            ")
        district21.add("Хэнтий аймаг  23020  Норовлин сум                             ")
        district21.add("Хэнтий аймаг  23030  Дадал сум                                ")
        district21.add("Хэнтий аймаг  23040  Батноров сум                             ")
        district21.add("Хэнтий аймаг  23060  Баянхутаг сум                            ")
        district21.add("Хэнтий аймаг  23070  Баян-Адарга сум                          ")
        district21.add("Хэнтий аймаг  23080  Галшир сум                               ")
        district21.add("Хэнтий аймаг  23100  Биндэр сум                               ")
        district21.add("Хэнтий аймаг  23130  Мөрөн сум                                ")
        district21.add("Хэнтий аймаг  23140  Өмнөдэлгэр сум                           ")
        district21.add("Хэнтий аймаг  23160  Батширээт сум                            ")
        district21.add("Хэнтий аймаг  23170  Баянмөнх сум                             ")
        district21.add("Хэнтий аймаг  23180  Дархан сум                               ")
        def district22 = []
        district22.add("Орхон аймаг  61020  Баян-Өндөр сум                            ")
        district22.add("Орхон аймаг  61010  Жаргалант сум                             ")

        data.putAt("Улаанбаатар",district1)
        data.putAt("Архангай",district2)
        data.putAt("Баян-Өлгий",district3);
        data.putAt("Баянхонгор",district4);
        data.putAt("Булган",district5);
        data.putAt("Говь Алтай",district6);
        data.putAt("Говьсүмбэр",district7);
        data.putAt("Дархан Уул",district8);
        data.putAt("Дорноговь",district9);
        data.putAt("Дорнод",district10);
        data.putAt("Дундговь",district11);
        data.putAt("Завхан",district12);
        data.putAt("Орхон",district22);
        data.putAt("Өвөрхангай",district13);
        data.putAt("Өмнөговь",district14);
        data.putAt("Сүхбаатар",district15);
        data.putAt("Сэлэнгэ",district16);
        data.putAt("Төв",district17);
        data.putAt("Увс",district18);
        data.putAt("Ховд",district19);
        data.putAt("Хөвсгөл",district20);
        data.putAt("Хэнтий",district21);

        return data

    }
    private List getAimagList(){
        def data = []
        def sumList = [:]
        data.add("Улаанбаатар");
        data.add("Баян-Өлгий");
        data.add("Баянхонгор");
        data.add("Булган");
        data.add("Говь Алтай");
        data.add("Говьсүмбэр");
        data.add("Дархан Уул");
        data.add("Дорноговь");
        data.add("Дорнод");
        data.add("Дундговь");
        data.add("Завхан");
        data.add("Орхон");
        data.add("Өвөрхангай");
        data.add("Өмнөговь");
        data.add("Сүхбаатар");
        data.add("Сэлэнгэ");
        data.add("Төв");
        data.add("Увс");
        data.add("Ховд");
        data.add("Хөвсгөл");
        data.add("Хэнтий");
        //controllerInfo.putAt()
        //data.add()
        return data;

    }
    private List getControllers(){

        def grailsApplication = new SysUser().domainClass.grailsApplication

        def data = []

        for (controller in grailsApplication.controllerClasses) {
            def controllerInfo = [:]

            controllerInfo.controller = controller.logicalPropertyName
            controllerInfo.controllerName = controller.fullName
            //println(controllerInfo)
            data << controllerInfo
        }
        return data

    }
    private Map getActionsByController(){

        def dataMap = [:]
        def data = grailsApplication.controllerClasses.collect { controller ->
            [controller: controller.logicalPropertyName,
            /* controllerName: controller.fullName,*/
             actions: controller.clazz.methods.findAll { it.getAnnotation(Action) }*.name.sort()]
        }

        data.each {dataMap.put(it.get("controller"),it.get("actions"))}

        println("[DATA]"+dataMap);

        return dataMap;

    }
    private List getActionMap(){

        def grailsApplication = new SysUser().domainClass.grailsApplication

        def data = []

        for (controller in grailsApplication.controllerClasses) {
            def controllerInfo = [:]

            controllerInfo.controller = controller.logicalPropertyName
            controllerInfo.controllerName = controller.fullName
            List actions = []

            BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(controller.newInstance())
            controller.uris.each {
                //  println it
                def actionInfo = [:]
                if(!it.contains("**")) {
                    def array = it.split("\\/")

                  if(array.size() == 3){
                      actions << array[2]
                  }


                }

            }
           /* for (pd in beanWrapper.propertyDescriptors) {

                String closureClassName = controller.getPropertyOrStaticPropertyOrFieldValue(pd.name, Closure)?.class?.name

                 if (closureClassName) actions << pd.name

            }  */
            controllerInfo.actions = actions.sort()
            data << controllerInfo
        }

        return data

    }
    def String getAppRootDir(String destinationDirectory){
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(destinationDirectory)
        return storagePath
    }
    private String getUploadedFileName(String url){
        if (url){
            def list = []
            list = url.split("/")
            println "============FILE neruud "+list[list.size()-1]
            return list[list.size()-1]
        }else return null
    }
    private String getFileExtention(String filname){
        if (filname){
            def list = []
            list = filname.split(".")
             println "============FILE neruud "+list
            if(!list)
            return null
            else return null
        }else return null
    }
    private String fhp()
    {
        def path = 'web-app/upload/test.txt';
        def f=new File(path);
        if(f.exists()) return 'web-app/upload/';
        else return 'webapps/teest/upload/';
    }
    public String safestr(String s){ //  scriptees zurgaana, mon urt temdectiig hasna
        if(s){
            s=s.replace("<script","");
            s=s.replace("</script","");
            s=s.replace("\"","'");
        }
        return s;

    }
    public int getRamdom(int seed){
        def r=new Random();
        return r.nextInt(seed);
    }
    public String strtomd5(String input){
        String res = "";

        try {
            if (input) {

            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            byte[] md5 = algorithm.digest();
            String tmp = "";
            for (int i = 0; i < md5.length; i++) {
                tmp = (Integer.toHexString(0xFF & md5[i]));
                if (tmp.length() == 1) {
                    res += "0" + tmp;
                } else {
                    res += tmp;
                }

            }
            }else{
                return null;
            }
        } catch (NoSuchAlgorithmException ex) { return null; }

        return res;

    }
    def downloader(String destinationDirectory,String filename){
        
        //  def basePath = grailsAttributes.getApplicationContext().getResource("/upload/").getFile().toString()
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath("upload")
        def file = new File(storagePath+"\\"+params.id)  
        if (file.exists()){
            // println "=============="+storagePath+params.id
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")
            response.outputStream << file.newDataInputStream()
        }
    }
    def String uploadFile(CommonsMultipartFile file,String name,String destinationDirectory){
        
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(destinationDirectory)
        def storagepathDirectory = new File(storagePath)
        storagePath =  storagePath+"//"
        println "----------------------file name"+name
       
        // def file = url
        if(!storagepathDirectory.exists()){
            print "create derectory"
            if(storagepathDirectory.mkdirs()){
                println "SUCCESS"
            }else{
                println "FAILED"
            }
        }
        if(file != null){
            
            file.transferTo(new File("${storagePath}${name}"))
            println "saved file to ${storagePath}${name}"
            return "${name}"
            
        }else{
            
            println "File ${storagePath}${name} was empty"
            return null;
            
        }
        
    }
	
}


