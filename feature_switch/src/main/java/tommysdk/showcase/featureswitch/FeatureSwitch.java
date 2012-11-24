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

import tommysdk.showcase.featureswitch.predicate.Feature;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * Interceptor for the <tt>@Disabled</tt> annotation. Works as a
 * feature switch, where the switch is considered to be open for
 * methods annotated with <tt>@Disabled</tt>. For the
 * <tt>@Disabled</tt> annotations to be evaluated, the containing
 * class has to be annotated with
 * <tt>@Interceptors(FeatureSwitch.class)</tt>.
 *
 * @author Tommy Tynj&auml;
 */
@Interceptor
@Disabled
public class FeatureSwitch {

    @Inject
    private FeatureManager featureManager;

    @AroundInvoke
    public Object intercept(final InvocationContext context) throws Exception {
        if (isDisabled(context.getMethod())) {
            return null;
        }
        else return context.proceed();
    }

    public boolean isDisabled(final Method method) {
        if (method.isAnnotationPresent(Disabled.class)) {
            Disabled a = method.getAnnotation(Disabled.class);
            Class<? extends Predicate> predicateClass = a.value();
            String[] properties = a.feature();
            try {
                validatePrerequisites(predicateClass);
                return predicateClass.newInstance().with(featureManager).isDisabled(properties);
            } catch (InstantiationException | IllegalAccessException e) {
                // TODO: Is throwing a runtime exception appropriate?
                throw new IllegalStateException("Unable to instantiate @Disabled predicate: " + predicateClass.getName());
            }
        }
        else return false;
    }

    private void validatePrerequisites(final Class<? extends Predicate> predicateClass) {
        if (Feature.class.equals(predicateClass)) {
            if (featureManager == null) {
                throw new IllegalStateException("No FeatureManager managed bean implementation found");
            }
        }
    }
}
