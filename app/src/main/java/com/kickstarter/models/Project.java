package com.kickstarter.models;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import com.kickstarter.libs.qualifiers.AutoGson;
import com.kickstarter.libs.utils.DateTimeUtils;
import com.kickstarter.libs.utils.IntegerUtils;
import com.kickstarter.libs.utils.NumberUtils;

import org.joda.time.DateTime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import auto.parcel.AutoParcel;

@AutoGson
@AutoParcel
public abstract class Project implements Parcelable {
  public abstract int backersCount();
  public abstract String blurb();
  public abstract @Nullable Backing backing();
  public abstract @Nullable Category category();
  public abstract @Nullable Integer commentsCount();
  public abstract String country(); // e.g.: US
  public abstract DateTime createdAt();
  public abstract User creator();
  public abstract String currency(); // e.g.: USD
  public abstract String currencySymbol(); // e.g.: $
  public abstract boolean currencyTrailingCode();
  public abstract @Nullable DateTime featuredAt();
  public abstract @Nullable List<User> friends();
  public abstract @Nullable DateTime deadline();
  public abstract float goal();
  public abstract long id(); // in the Kickstarter app, this is project.pid not project.id
  public abstract boolean isBacking();
  public abstract boolean isStarred();
  public abstract @Nullable DateTime launchedAt();
  public abstract @Nullable Location location();
  public abstract String name();
  public abstract float pledged();
  public abstract @Nullable Photo photo();
  public abstract @Nullable DateTime potdAt();
  public abstract @Nullable String slug();
  public abstract @State String state();
  public abstract @Nullable DateTime stateChangedAt();
  public abstract @Nullable Float staticUsdRate();
  public abstract @Nullable Integer updatesCount();
  public abstract @Nullable List<Reward> rewards();
  public abstract DateTime updatedAt();
  public abstract Urls urls();
  public abstract @Nullable Video video();

  @AutoParcel.Builder
  public abstract static class Builder {
    public abstract Builder backersCount(int __);
    public abstract Builder blurb(String __);
    public abstract Builder backing(Backing __);
    public abstract Builder category(Category __);
    public abstract Builder commentsCount(Integer __);
    public abstract Builder country(String __);
    public abstract Builder createdAt(DateTime __);
    public abstract Builder creator(User __);
    public abstract Builder currency(String __);
    public abstract Builder currencySymbol(String __);
    public abstract Builder currencyTrailingCode(boolean __);
    public abstract Builder deadline(DateTime __);
    public abstract Builder featuredAt(DateTime __);
    public abstract Builder friends(List<User> __);
    public abstract Builder goal(float __);
    public abstract Builder id(long __);
    public abstract Builder isBacking(boolean __);
    public abstract Builder isStarred(boolean __);
    public abstract Builder launchedAt(DateTime __);
    public abstract Builder location(Location __);
    public abstract Builder name(String __);
    public abstract Builder pledged(float __);
    public abstract Builder photo(Photo __);
    public abstract Builder potdAt(DateTime __);
    public abstract Builder rewards(List<Reward> __);
    public abstract Builder slug(String __);
    public abstract Builder staticUsdRate(Float __);
    public abstract Builder state(@State String __);
    public abstract Builder stateChangedAt(DateTime __);
    public abstract Builder updatedAt(DateTime __);
    public abstract Builder updatesCount(Integer __);
    public abstract Builder urls(Urls __);
    public abstract Builder video(Video __);
    public abstract Project build();
  }

  public static Builder builder() {
    return new AutoParcel_Project.Builder()
      .isBacking(false)
      .isStarred(false)
      .rewards(new ArrayList<>());
  }

  public abstract Builder toBuilder();

  public static final String STATE_STARTED      = "started";
  public static final String STATE_SUBMITTED    = "submitted";
  public static final String STATE_LIVE         = "live";
  public static final String STATE_SUCCESSFUL   = "successful";
  public static final String STATE_FAILED       = "failed";
  public static final String STATE_CANCELED     = "canceled";
  public static final String STATE_SUSPENDED    = "suspended";
  public static final String STATE_PURGED       = "purged";

  @Retention(RetentionPolicy.SOURCE)
  @StringDef({STATE_STARTED, STATE_SUBMITTED, STATE_LIVE, STATE_SUCCESSFUL, STATE_FAILED, STATE_CANCELED, STATE_SUSPENDED, STATE_PURGED})
  public @interface State {}

  public @NonNull String creatorBioUrl() {
    return urls().web().creatorBio();
  }

  public boolean isBackingRewardId(final long rewardId) {
    return this.backing() != null && this.backing().rewardId() != null && this.backing().rewardId() == rewardId;
  }

  public @NonNull String descriptionUrl() {
    return urls().web().description();
  }

  public @NonNull String updatesUrl() {
    return urls().web().updates();
  }

  public @NonNull String webProjectUrl() {
    return urls().web().project();
  }

  @AutoParcel
  @AutoGson
  public abstract static class Urls implements Parcelable {
    public abstract Web web();
    @Nullable public abstract Api api();

    @AutoParcel.Builder
    public abstract static class Builder {
      public abstract Builder web(Web __);
      public abstract Builder api(Api __);
      public abstract Urls build();
    }

    public static Builder builder() {
      return new AutoParcel_Project_Urls.Builder();
    }

    public abstract Builder toBuilder();

    @AutoParcel
    @AutoGson
    public abstract static class Web implements Parcelable {
      public abstract String project();
      @Nullable public abstract String projectShort();
      public abstract String rewards();
      @Nullable public abstract String updates();

      @AutoParcel.Builder
      public abstract static class Builder {
        public abstract Builder project(String __);
        public abstract Builder projectShort(String __);
        public abstract Builder rewards(String __);
        public abstract Builder updates(String __);
        public abstract Web build();
      }

      public static Builder builder() {
        return new AutoParcel_Project_Urls_Web.Builder();
      }

      public abstract Builder toBuilder();

      public @NonNull String creatorBio() {
        return Uri.parse(project())
          .buildUpon()
          .appendEncodedPath("/creator_bio")
          .toString();
      }

      public @NonNull String description() {
        return Uri.parse(project())
          .buildUpon()
          .appendEncodedPath("/description")
          .toString();
      }
    }

    @AutoParcel
    @AutoGson
    public abstract static class Api implements Parcelable {
      @Nullable public abstract String project();
      @Nullable public abstract String comments();
      @Nullable public abstract String updates();

      @AutoParcel.Builder
      public abstract static class Builder {
        public abstract Builder project(String __);
        public abstract Builder comments(String __);
        public abstract Builder updates(String __);
        public abstract Api build();
      }

      public static Builder builder() {
        return new AutoParcel_Project_Urls_Api.Builder();
      }

      public abstract Builder toBuilder();
    }
  }

  public boolean hasComments() {
    return IntegerUtils.isNonZero(this.commentsCount());
  }

  public boolean hasRewards() {
    return rewards() != null;
  }

  public boolean hasVideo() {
    return video() != null;
  }

  /** Returns whether the project is in a canceled state. */
  public boolean isCanceled() {
    return STATE_CANCELED.equals(state());
  }

  /** Returns whether the project is in a failed state. */
  public boolean isFailed() {
    return STATE_FAILED.equals(state());
  }

  public boolean isFeaturedToday() {
    if (featuredAt() == null) {
      return false;
    }

    return DateTimeUtils.isDateToday(featuredAt());
  }

  /** Returns whether the project is in a live state. */
  public boolean isLive() {
    return STATE_LIVE.equals(state());
  }

  public boolean isFriendBacking() {
    return friends() != null && friends().size() > 0;
  }

  public boolean isFunded() {
    return isLive() && (percentageFunded() >= 100);
  }

  public boolean isPotdToday() {
    if (potdAt() == null) {
      return false;
    }

    return DateTimeUtils.isDateToday(potdAt());
  }

  /** Returns whether the project is in a purged state. */
  public boolean isPurged() {
    return STATE_PURGED.equals(state());
  }

  /** Returns whether the project is in a live state. */
  public boolean isStarted() {
    return STATE_STARTED.equals(state());
  }

  /** Returns whether the project is in a submitted state. */
  public boolean isSubmitted() {
    return STATE_SUBMITTED.equals(state());
  }

  /** Returns whether the project is in a suspended state. */
  public boolean isSuspended() {
    return STATE_SUSPENDED.equals(state());
  }

  /** Returns whether the project is in a successful state. */
  public boolean isSuccessful() {
    return STATE_SUCCESSFUL.equals(state());
  }

  public float percentageFunded() {
    if (goal() > 0.0f) {
      return (pledged() / goal()) * 100.0f;
    }

    return 0.0f;
  }

  public @NonNull String param() {
    final String slug = slug();
    return slug != null ? slug : String.valueOf(id());
  }

  public @NonNull String secureWebProjectUrl() {
    // TODO: Just use http with local env
    return Uri.parse(webProjectUrl()).buildUpon().scheme("https").build().toString();
  }

  public @NonNull String newPledgeUrl() {
    return Uri.parse(secureWebProjectUrl()).buildUpon().appendEncodedPath("pledge/new").toString();
  }

  public @NonNull String editPledgeUrl() {
    return Uri.parse(secureWebProjectUrl()).buildUpon().appendEncodedPath("pledge/edit").toString();
  }

  public @NonNull String rewardSelectedUrl(final @NonNull Reward reward) {
    return Uri.parse(newPledgeUrl())
      .buildUpon().scheme("https")
      .appendQueryParameter("backing[backer_reward_id]", String.valueOf(reward.id()))
      .appendQueryParameter("clicked_reward", "true")
      .build()
      .toString();
  }

  @Override
  public final @NonNull String toString() {
    return "Project{"
      + "id=" + id() + ", "
      + "name=" + name() + ", "
      + "}";
  }

  @Override
  public final boolean equals(final @Nullable Object o) {
    if (o != null && o instanceof Project) {
      final Project p = (Project)o;
      return id() == p.id();
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return (int)id();
  }
}
