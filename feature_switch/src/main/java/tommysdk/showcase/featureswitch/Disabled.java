/*
* Copyright 2012 Tommy Tynjä
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tommysdk.showcase.featureswitch;

import tommysdk.showcase.featureswitch.predicate.Always;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A method marked with this annotation is considered disabled. When
 * used in conjunction with the <tt>FeatureSwitch</tt> interceptor,
 * the execution of this method will be disabled and <tt>null</tt>
 * will be returned.
 *
 * @author Tommy Tynj&auml;
 * @see FeatureSwitch
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface Disabled {

    Class<? extends Predicate> value() default Always.class;
    String[] feature() default "";

}
