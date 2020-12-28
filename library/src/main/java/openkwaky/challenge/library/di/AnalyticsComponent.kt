package openkwaky.challenge.library.di

import dagger.Component
import openkwaky.challenge.library.Analytics

@Component(modules = [AnalyticsModule::class])
interface AnalyticsComponent {
    object Initializer {
        fun init(analytics: Analytics): AnalyticsComponent {
            return DaggerAnalyticsComponent.builder().analyticsModule(AnalyticsModule(analytics)).build()

        }
    }

    fun inject(analytics: Analytics)
}
