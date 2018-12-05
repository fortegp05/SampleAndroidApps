package com.example.fortegp05.sampleandroidapps.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubRepositoryApiResponseEntity {

    String total_count;
    String incomplete_results;
    List<Repository> items;

    public class Repository {
        String id;
        String node_id;
        String name;
        String full_name;
        @SerializedName("private")
        String privatee;
        Owner owner;
        String html_url;
        String description;
        String fork;
        String url;
        String forks_url;
        String keys_url;
        String collaborators_url;
        String teams_url;
        String hooks_url;
        String issue_events_url;
        String events_url;
        String assignees_url;
        String branches_url;
        String tags_url;
        String blobs_url;
        String git_tags_url;
        String git_refs_url;
        String trees_url;
        String statuses_url;
        String languages_url;
        String stargazers_url;
        String contributors_url;
        String subscribers_url;
        String subscription_url;
        String commits_url;
        String git_commits_url;
        String comments_url;
        String issue_comment_url;
        String contents_url;
        String compare_url;
        String merges_url;
        String archive_url;
        String downloads_url;
        String issues_url;
        String pulls_url;
        String milestones_url;
        String notifications_url;
        String labels_url;
        String releases_url;
        String deployments_url;
        String created_at;
        String updated_at;
        String pushed_at;
        String git_url;
        String ssh_url;
        String clone_url;
        String svn_url;
        String homepage;
        String size;
        String stargazers_count;
        String watchers_count;
        String language;
        String has_issues;
        String has_projects;
        String has_downloads;
        String has_wiki;
        String has_pages;
        String forks_count;
        String mirror_url;
        String archived;
        String open_issues_count;
        License license;
        String forks;
        String open_issues;
        String watchers;
        String default_branch;
        String score;

        public String getId() {
            return id;
        }

        public String getNode_id() {
            return node_id;
        }

        public String getName() {
            return name;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getPrivatee() {
            return privatee;
        }

        public Owner getOwner() {
            return owner;
        }

        public String getHtml_url() {
            return html_url;
        }

        public String getDescription() {
            return description;
        }

        public String getFork() {
            return fork;
        }

        public String getUrl() {
            return url;
        }

        public String getForks_url() {
            return forks_url;
        }

        public String getKeys_url() {
            return keys_url;
        }

        public String getCollaborators_url() {
            return collaborators_url;
        }

        public String getTeams_url() {
            return teams_url;
        }

        public String getHooks_url() {
            return hooks_url;
        }

        public String getIssue_events_url() {
            return issue_events_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public String getAssignees_url() {
            return assignees_url;
        }

        public String getBranches_url() {
            return branches_url;
        }

        public String getTags_url() {
            return tags_url;
        }

        public String getBlobs_url() {
            return blobs_url;
        }

        public String getGit_tags_url() {
            return git_tags_url;
        }

        public String getGit_refs_url() {
            return git_refs_url;
        }

        public String getTrees_url() {
            return trees_url;
        }

        public String getStatuses_url() {
            return statuses_url;
        }

        public String getLanguages_url() {
            return languages_url;
        }

        public String getStargazers_url() {
            return stargazers_url;
        }

        public String getContributors_url() {
            return contributors_url;
        }

        public String getSubscribers_url() {
            return subscribers_url;
        }

        public String getSubscription_url() {
            return subscription_url;
        }

        public String getCommits_url() {
            return commits_url;
        }

        public String getGit_commits_url() {
            return git_commits_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public String getIssue_comment_url() {
            return issue_comment_url;
        }

        public String getContents_url() {
            return contents_url;
        }

        public String getCompare_url() {
            return compare_url;
        }

        public String getMerges_url() {
            return merges_url;
        }

        public String getArchive_url() {
            return archive_url;
        }

        public String getDownloads_url() {
            return downloads_url;
        }

        public String getIssues_url() {
            return issues_url;
        }

        public String getPulls_url() {
            return pulls_url;
        }

        public String getMilestones_url() {
            return milestones_url;
        }

        public String getNotifications_url() {
            return notifications_url;
        }

        public String getLabels_url() {
            return labels_url;
        }

        public String getReleases_url() {
            return releases_url;
        }

        public String getDeployments_url() {
            return deployments_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getPushed_at() {
            return pushed_at;
        }

        public String getGit_url() {
            return git_url;
        }

        public String getSsh_url() {
            return ssh_url;
        }

        public String getClone_url() {
            return clone_url;
        }

        public String getSvn_url() {
            return svn_url;
        }

        public String getHomepage() {
            return homepage;
        }

        public String getSize() {
            return size;
        }

        public String getStargazers_count() {
            return stargazers_count;
        }

        public String getWatchers_count() {
            return watchers_count;
        }

        public String getLanguage() {
            return language;
        }

        public String getHas_issues() {
            return has_issues;
        }

        public String getHas_projects() {
            return has_projects;
        }

        public String getHas_downloads() {
            return has_downloads;
        }

        public String getHas_wiki() {
            return has_wiki;
        }

        public String getHas_pages() {
            return has_pages;
        }

        public String getForks_count() {
            return forks_count;
        }

        public String getMirror_url() {
            return mirror_url;
        }

        public String getArchived() {
            return archived;
        }

        public String getOpen_issues_count() {
            return open_issues_count;
        }

        public License getLicense() {
            return license;
        }

        public String getForks() {
            return forks;
        }

        public String getOpen_issues() {
            return open_issues;
        }

        public String getWatchers() {
            return watchers;
        }

        public String getDefault_branch() {
            return default_branch;
        }

        public String getScore() {
            return score;
        }
    }

    public class Owner {
        String login;
        String id;
        String node_id;
        String avatar_url;
        String gravatar_id;
        String url;
        String html_url;
        String followers_url;
        String following_url;
        String gists_url;
        String starred_url;
        String subscriptions_url;
        String organizations_url;
        String repos_url;
        String events_url;
        String received_events_url;
        String type;
        String site_admin;
    }

    public class License {
        String key;
        String name;
        String spdx_id;
        String url;
        String node_id;
    }

    ;

    public List<Repository> getItems() {
        return items;
    }
}
