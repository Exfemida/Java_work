package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos()
            .get(new Coordinates.Simple("Exfemida", "Java_work")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String,String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
